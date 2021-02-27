package com.osyangxin.moji.sql;


import com.github.pagehelper.Dialect;
import com.github.pagehelper.PageException;
import com.github.pagehelper.cache.Cache;
import com.github.pagehelper.cache.CacheFactory;
import com.github.pagehelper.util.MSUtils;
import com.github.pagehelper.util.StringUtil;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;


/**
 * 分页拦截+SQL日志
 *
 * @author fengjianshe
 */
@Intercepts(
        {
                @Signature(type = Executor.class, method = "update",
                        args = {MappedStatement.class,
                                Object.class}),
                @Signature(type = Executor.class, method = "query",
                        args = {MappedStatement.class,
                                Object.class, RowBounds.class, ResultHandler.class})
        }
)
public class CustomSQLInterceptor extends SqlLogHandler implements Interceptor {

    //缓存count查询的ms
    protected Cache<String, MappedStatement> msCountMap = null;
    private Dialect dialect;
    private String default_dialect_class = "CustomPageHelper";
    private Field additionalParametersField;
    private String countSuffix = "_COUNT";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            Object[] args = invocation.getArgs();
            if (2 == args.length) {
                MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
                Object parameter = null;
                if (invocation.getArgs().length > 1) {
                    parameter = invocation.getArgs()[1];
                }
                String sqlId = mappedStatement.getId();
                BoundSql boundSql = mappedStatement.getBoundSql(parameter);
                Configuration configuration = mappedStatement.getConfiguration();
                Object returnValue;
                long start = System.currentTimeMillis();
                returnValue = invocation.proceed();
                long end = System.currentTimeMillis();
                long time = (end - start);
                printSql(configuration, boundSql, sqlId, time);
                return returnValue;
            } else {
                MappedStatement ms = (MappedStatement) args[0];
                Object parameter = args[1];
                RowBounds rowBounds = (RowBounds) args[2];
                ResultHandler resultHandler = (ResultHandler) args[3];
                Executor executor = (Executor) invocation.getTarget();
                CacheKey cacheKey;
                BoundSql boundSql;
                //由于逻辑关系，只会进入一次
                if (args.length == 4) {
                    //4 个参数时
                    boundSql = ms.getBoundSql(parameter);
                    cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
                } else {
                    //6 个参数时
                    cacheKey = (CacheKey) args[4];
                    boundSql = (BoundSql) args[5];
                }
                List resultList;
                //调用方法判断是否需要进行分页，如果不需要，直接返回结果
                if (!dialect.skip(ms, parameter, rowBounds)) {
                    //反射获取动态参数
                    String msId = ms.getId();
                    Configuration configuration = ms.getConfiguration();
                    Map<String, Object> additionalParameters = (Map<String, Object>) additionalParametersField.get(boundSql);
                    //判断是否需要进行 count 查询
                    if (dialect.beforeCount(ms, parameter, rowBounds)) {
                        String countMsId = msId + countSuffix;
                        Long count;
                        //先判断是否存在手写的 count 查询
                        MappedStatement countMs = getExistedMappedStatement(configuration, countMsId);
                        if (countMs != null) {
                            count = executeManualCount(executor, countMs, parameter, boundSql, resultHandler);
                        } else {
                            countMs = msCountMap.get(countMsId);
                            //自动创建
                            if (countMs == null) {
                                //根据当前的 ms 创建一个返回值为 Long 类型的 ms
                                countMs = MSUtils.newCountMappedStatement(ms, countMsId);
                                msCountMap.put(countMsId, countMs);
                            }
                            count = executeAutoCount(executor, countMs, parameter, boundSql, rowBounds,
                                    resultHandler);
                        }
                        //处理查询总数
                        //返回 true 时继续分页查询，false 时直接返回
                        if (!dialect.afterCount(count, parameter, rowBounds)) {
                            //当查询总数为 0 时，直接返回空的结果
                            return dialect.afterPage(new ArrayList(), parameter, rowBounds);
                        }
                    }
                    //判断是否需要进行分页查询
                    if (dialect.beforePage(ms, parameter, rowBounds)) {
                        //生成分页的缓存 key
                        CacheKey pageKey = cacheKey;
                        //处理参数对象
                        parameter = dialect.processParameterObject(ms, parameter, boundSql, pageKey);
                        //调用方言获取分页 sql
                        String pageSql = dialect.getPageSql(ms, boundSql, parameter, rowBounds, pageKey);
                        BoundSql pageBoundSql = new BoundSql(configuration, pageSql,
                                boundSql.getParameterMappings(), parameter);
                        //设置动态参数
                        for (String key : additionalParameters.keySet()) {
                            pageBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
                        }
                        //执行分页查询
                        long start = System.currentTimeMillis();
                        resultList = executor
                                .query(ms, parameter, RowBounds.DEFAULT, resultHandler, pageKey, pageBoundSql);
                        long end = System.currentTimeMillis();
                        long time = (end - start);
                        printSql(ms.getConfiguration(), pageBoundSql, ms.getId(), time);
                    } else {
                        //不执行分页的情况下，也不执行内存分页
                        long start = System.currentTimeMillis();
                        resultList = executor
                                .query(ms, parameter, RowBounds.DEFAULT, resultHandler, cacheKey, boundSql);
                        long end = System.currentTimeMillis();
                        long time = (end - start);
                        printSql(ms.getConfiguration(), boundSql, ms.getId(), time);

                    }
                } else {
                    //rowBounds用参数值，不使用分页插件处理时，仍然支持默认的内存分页
                    long start = System.currentTimeMillis();
                    resultList = executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
                    long end = System.currentTimeMillis();
                    long time = (end - start);
                    printSql(ms.getConfiguration(), boundSql, ms.getId(), time);
                }
                return dialect.afterPage(resultList, parameter, rowBounds);
            }
        } finally {
            dialect.afterAll();
        }
    }

    /**
     * 执行手动设置的 count 查询，该查询支持的参数必须和被分页的方法相同
     */
    private Long executeManualCount(Executor executor, MappedStatement countMs,
                                    Object parameter, BoundSql boundSql,
                                    ResultHandler resultHandler) throws IllegalAccessException, SQLException {
        CacheKey countKey = executor.createCacheKey(countMs, parameter, RowBounds.DEFAULT, boundSql);
        BoundSql countBoundSql = countMs.getBoundSql(parameter);
        long start = System.currentTimeMillis();
        Object countResultList = executor
                .query(countMs, parameter, RowBounds.DEFAULT, resultHandler, countKey, countBoundSql);
        long end = System.currentTimeMillis();
        long time = (end - start);
        printSql(countMs.getConfiguration(), boundSql, countMs.getId(), time);
        Long count = ((Number) ((List) countResultList).get(0)).longValue();
        return count;
    }

    /**
     * 执行自动生成的 count 查询
     */
    private Long executeAutoCount(Executor executor, MappedStatement countMs,
                                  Object parameter, BoundSql boundSql,
                                  RowBounds rowBounds, ResultHandler resultHandler)
            throws IllegalAccessException, SQLException {
        Map<String, Object> additionalParameters = (Map<String, Object>) additionalParametersField
                .get(boundSql);
        //创建 count 查询的缓存 key
        CacheKey countKey = executor.createCacheKey(countMs, parameter, RowBounds.DEFAULT, boundSql);
        //调用方言获取 count sql
        String countSql = dialect.getCountSql(countMs, boundSql, parameter, rowBounds, countKey);
        //countKey.update(countSql);
        BoundSql countBoundSql = new BoundSql(countMs.getConfiguration(), countSql,
                boundSql.getParameterMappings(), parameter);
        //当使用动态 SQL 时，可能会产生临时的参数，这些参数需要手动设置到新的 BoundSql 中
        for (String key : additionalParameters.keySet()) {
            countBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
        }
        //执行 count 查询
        long start = System.currentTimeMillis();
        Object countResultList = executor
                .query(countMs, parameter, RowBounds.DEFAULT, resultHandler, countKey, countBoundSql);
        long end = System.currentTimeMillis();
        long time = (end - start);
        printSql(countMs.getConfiguration(), boundSql, countMs.getId(), time);
        Long count = (Long) ((List) countResultList).get(0);
        return count;
    }

    /**
     * 尝试获取已经存在的在 MS，提供对手写count和page的支持
     */
    private MappedStatement getExistedMappedStatement(Configuration configuration, String msId) {
        MappedStatement mappedStatement = null;
        try {
            mappedStatement = configuration.getMappedStatement(msId, false);
        } catch (Throwable t) {
            //ignore
        }
        return mappedStatement;
    }

    @Override
    public Object plugin(Object target) {
        //Spring bean 方式配置时，如果没有配置属性就不会执行下面的 setProperties 方法，就不会初始化，因此考虑在这个方法中做一次判断和初始化
        //https://github.com/pagehelper/Mybatis-PageHelper/issues/26
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        //缓存 count ms
        msCountMap = CacheFactory.createCache(properties.getProperty("msCountCache"), "ms", properties);
        String dialectClass = properties.getProperty("dialect");
        if (StringUtil.isEmpty(dialectClass)) {
            dialectClass = default_dialect_class;
        }
        try {
            Class<?> aClass = Class.forName(dialectClass);
            dialect = (Dialect) aClass.newInstance();
        } catch (Exception e) {
            throw new PageException(e);
        }
        dialect.setProperties(properties);

        String countSuffix = properties.getProperty("countSuffix");
        if (StringUtil.isNotEmpty(countSuffix)) {
            this.countSuffix = countSuffix;
        }

        try {
            //反射获取 BoundSql 中的 additionalParameters 属性
            additionalParametersField = BoundSql.class.getDeclaredField("additionalParameters");
            additionalParametersField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new PageException(e);
        }
    }

}
