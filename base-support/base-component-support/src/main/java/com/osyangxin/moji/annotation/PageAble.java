package com.osyangxin.moji.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PageAble {

    String pageSizeName() default "size";

    String pageNumName() default "page";

    int pageSize() default 20;

    int pageNum() default 1;

}
