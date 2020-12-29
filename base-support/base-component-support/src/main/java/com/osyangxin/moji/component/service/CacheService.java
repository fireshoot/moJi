package com.osyangxin.moji.component.service;

import com.osyangxin.moji.common.utils.SerializeUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.connection.RedisGeoCommands.GeoRadiusCommandArgs;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

/**
 * 方法实现说明 : redis缓存统一服务
 * @author      yangxin
 * @date        2020/12/29 18:33
*/
@Service
public class CacheService implements BaseCacheService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    private ValueOperations<String, String> intOps;

    /**
     * 存入set
     */
    @Override
    public void addToSet(String key, String value) {
        stringRedisTemplate.opsForSet().add(key, value);
    }

    /**
     * 获取set内容
     */
    @Override
    public Set<String> getSetMember(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    /**
     * 批量获取
     */
    public List<String> mget(Collection<String> keys) {
        return stringRedisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * 获取位置信息
     */
    public List<Point> geoPosition(String key, Object value) {
        return redisTemplate.opsForGeo().position(key, value);
    }

    /**
     * 添加位置信息
     */
    public long geoAdd(String key, Point point, Object value) {
        return redisTemplate.opsForGeo().add(key, point, value);
    }

    /**
     * 获取区域内的位置信息
     */
    public GeoResults<RedisGeoCommands.GeoLocation<Integer>> geoRadius(String key, Circle circle, GeoRadiusCommandArgs args) {
        return redisTemplate.opsForGeo().radius(key, circle, args);
    }

    /**
     * 删除zset中的数据.
     */
    public Long zRemove(String key, Object... objects) {
        return redisTemplate.opsForZSet().remove(key, objects);
    }

    /**
     * 获取zset的size.
     */
    public Long zSize(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 获取字典所有key值
     *
     * @param key
     * @return
     */
    public Set<Object> getHashKeyset(String key) {
        return stringRedisTemplate.opsForHash().keys(key);
    }

    /**
     * 字典类型删除操作
     */
    public Long delFromMap(String key, String itemKey) {
        return stringRedisTemplate.opsForHash().delete(key, itemKey);
    }

    public Map<Object, Object> getMapEntries(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }

    /**
     * 字符串类型写操作 设置<string,object>
     */
    @Override
    public void setVal(String key, Object val) {
        stringRedisTemplate.opsForValue().set(key, SerializeUtil.serialize(val));
    }

    /**
     * 字符串类型写操作 设置<string,object>
     *
     * @param expire 过期时间
     */
    @Override
    public void setVal(String key, Object val, Long expire) {
        stringRedisTemplate.opsForValue()
                .set(key, SerializeUtil.serialize(val), expire, TimeUnit.SECONDS);
    }

    /**
     * 字符串类型读操作 获取反序列化对象
     */
    @Override
    public <T> T getVal(String key, Class<T> clazz) {
        return SerializeUtil.deserialize(stringRedisTemplate.opsForValue().get(key), clazz);
    }

    /**
     * 字符串类型读操作 获取缺省为string的反序列化对象
     */
    @Override
    public String getVal(String key) {
        return getVal(key, String.class);
    }

    /**
     * 整数类型写操作 为当前值+1并设置超时时间
     */
    public void increase(String key, Long expire) {
        if (intOps == null) {
            intOps = stringRedisTemplate.opsForValue();
        }
        intOps.increment(key, 1L);
        if (expire != null) {
            stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 整数类型写操作 为当前值-1并设置超时时间
     */
    public void decrease(String key, Long expire) {
        if (intOps == null) {
            intOps = stringRedisTemplate.opsForValue();
        }
        intOps.increment(key, -1L);
        if (expire != null) {
            stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 列表类型写操作 向指定队列尾部添加值
     */
    @Override
    public void rightPush(String key, Object val) {
        stringRedisTemplate.opsForList().rightPush(key, SerializeUtil.serialize(val));
    }

    /**
     * 列表类型写操作 向指定队列尾部添加值
     *
     * @param expire 过期时间
     */
    @Override
    public void rightPush(String key, Object val, Long expire) {
        stringRedisTemplate.opsForList().rightPush(key, SerializeUtil.serialize(val));
        stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public <T> void rightPushAll(String key, List<T> list) {
        redisTemplate.opsForList().rightPushAll(list);
    }

    /**
     * 列表类型读操作 返回一个指定反序列化对象的队列元素集合
     */
    @Override
    public <T> List<T> range(String key, Integer start, Integer end, Class<T> clazz) {
        List<String> stringList = stringRedisTemplate.opsForList().range(key, start, end);
        List<T> list = new ArrayList<>();
        for (String json : stringList) {
            list.add(SerializeUtil.deserialize(json, clazz));
        }
        return list;
    }

    /**
     * 列表类型读操作 返回缺省的对象队列结合
     */
    @Override
    public List<String> range(String key, Integer start, Integer end) {
        List<String> stringList = stringRedisTemplate.opsForList().range(key, start, end);
        return stringList;
    }

    @Override
    public Long removeFromList(String key, Integer start, Integer end) {
        return stringRedisTemplate.opsForList().remove(key, start, end);
    }

    /**
     * 队列头部类型读操作
     */
    @Override
    public <T> T pop(String key, Class<T> clazz) {
        String json = stringRedisTemplate.opsForList().leftPop(key);
        return SerializeUtil.deserialize(json, clazz);

    }

    /**
     * 字典类型写操作
     */
    @Override
    public void putToMap(String key, Object itemKey, Object item) {
        stringRedisTemplate.opsForHash().put(key, itemKey, SerializeUtil.serialize(item));
    }

    /**
     * 字典类型写操作
     */
    @Override
    public void putToMap(String key, Object itemKey, Object item, Long expire) {
        stringRedisTemplate.opsForHash().put(key, itemKey, SerializeUtil.serialize(item));
        stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * 字典类型读操作
     */
    @Override
    public <T> T getFromMap(String key, Object itemKey, Class<T> clazz) {
        Object val = stringRedisTemplate.opsForHash().get(key, itemKey);
        if (val == null) {
            return null;
        }
        return SerializeUtil.deserialize(val.toString(), clazz);
    }


    /**
     * 删除指定的key值
     */
    @Override
    public void deleteVal(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 同步写
     */
    @Override
    public Boolean setIfAbsent(String key, Object val) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, SerializeUtil.serialize(val));
    }

    @Override
    public Boolean setIfAbsent(String key, Object val, long timeout, TimeUnit unit) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, SerializeUtil.serialize(val), timeout, unit);
    }

    /**
     * getset
     */
    @Override
    public <T> T getAndSet(String key, Object val, Class<T> clazz) {
        return SerializeUtil
                .deserialize(stringRedisTemplate.opsForValue().getAndSet(key, SerializeUtil.serialize(val)),
                        clazz);
    }

    /**
     * @param key
     * @param val
     * @param <T>
     * @return
     */
    @Override
    public <T> T getAndSet(String key, T val) {
        return (T) getAndSet(key, val, val.getClass());
    }

    /**
     * 发布消息
     */
    @Override
    public void sendMessage(String channel, Object message) {
        stringRedisTemplate.convertAndSend(channel, SerializeUtil.serialize(message));
    }

    @Override
    public void batchSetVal(Map<String, String> cacheKeyValues) {
        stringRedisTemplate.opsForValue().multiSet(cacheKeyValues);
    }

    /**
     * 获取缓存的系统时间
     */
    @Override
    public Long getCurrentTimeMilliForCache() {

        return stringRedisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.time();
            }
        });
    }

    //为哈希表 key 中的指定字段的整数值加上增量 increment
    public Long hashIncr(String key, Object field, long delta) {
        return redisTemplate.opsForHash().increment(key, field, delta);
    }

    public Long hashIncrOne(String key, String field) {
        return redisTemplate.opsForHash().increment(key, field, 1L);
    }

    /**
     * 字典类型批量写操作
     */
    public void hsetall(String key, Map itemKey) {
        redisTemplate.opsForHash().putAll(key, itemKey);
    }


    public Map<String, String> hgetall(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

}