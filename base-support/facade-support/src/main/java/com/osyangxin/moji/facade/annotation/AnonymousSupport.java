package com.osyangxin.moji.facade.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法实现说明
 * @author      yangxin
 * @date        2020/12/29 18:14
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousSupport {

    String content() default "header";

    String key() default "token";

}
