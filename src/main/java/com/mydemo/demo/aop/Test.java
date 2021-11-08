package com.mydemo.demo.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
     boolean required() default true;
}
