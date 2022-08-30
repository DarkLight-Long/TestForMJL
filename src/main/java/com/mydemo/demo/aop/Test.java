package com.mydemo.demo.aop;

import java.lang.annotation.*;

/**
 * @author 1
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
     boolean required() default true;

     String name() default "";
}
