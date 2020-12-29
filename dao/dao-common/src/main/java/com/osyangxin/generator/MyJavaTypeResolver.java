package com.osyangxin.generator;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

/**
 * Mybatis生成代码类型自定义转换
 */
public class MyJavaTypeResolver extends JavaTypeResolverDefaultImpl {

    /**
     * 将tinyint转换为Integer
     */
    public MyJavaTypeResolver() {
        super();
        super.typeMap.put(-6, new JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Short.class.getName())));
    }
}