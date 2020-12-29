package com.osyangxin.moji.facade.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @author Cris
* @description 原始类型 添加此注解，Controller返回值将不被ResponseAdvice拦截
* @date 2019/9/20
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RawType {

}