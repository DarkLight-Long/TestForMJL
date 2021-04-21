package com.mydemo.test;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestForAnnotation {

    @AliasFor(value = "value")
    // 相当于value(把值复制给value)
    String path() default "";

    String value() default "";

    int index() default -1;
}
