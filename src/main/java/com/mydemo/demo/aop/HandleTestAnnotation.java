package com.mydemo.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class HandleTestAnnotation {

    /**
     * @Pointcue 可以相互合并用 && 连接
     */
    /**
     * explain: public * functitonName(..) =>{
     *          0. public
     *          1. * => 任意返回值类型
     *          2. functionName() => 方法名
     *          3. (..) => 任意个入参
     *      }
     *
     * replaced:
     *      1. com.mydemo.service.*.*(..) => 方法层级体现出来了,满足层级的方法
     *         com.mydemo.service..*(..)  =>包或子包中的任意方法
     *      2. * set*(..) => 以set开头的方法
     *
     */
    @Pointcut("execution(public * functitonName(..))")
    public void pointCutA() {}

    /**
     * explain: com.mydemo.demo.service..* => 包或子包下任意的服务
     *          com.mydemo.demo.service.SysUserService => SysUserController服务
     *
     */
    @Pointcut("within(com.mydemo.demo.service..*) ")
    public void pointCutB() {}

    /**
     * 指定
     */
//    @Pointcut("this(com.mydemo.demo.service.SysUserService)")
    @Pointcut("target(com.mydemo.demo.service.SysUserService)")
    public void pointCutC() {

    }

    /** the args version matches if the argument passed at runtime is Serializable, 运行时传递的为*/
    @Pointcut("args(java.io.Serializable)")
//    @Pointcut("execution(* functionName(java.io.Serializable))")
    /** the execution version matches if the method signature declares a single parameter of type Serializable. 定义的参数 */
    public void pointCutD() {}

    /**
     * 可替换
     */
    @Pointcut("annotation(org.springframework.transaction.annotation.Transactional)")
//    @Pointcut("target(org.springframework.transaction.annotation.Transactional)")
//    @Pointcut("within(org.springframework.transaction.annotation.Transactional)")
    public void pointCutE() {}


    /**
     * pointCutA 所监听方法触发调用前
     * @param joinPoint
     */
    @Before("pointCutA()")
    public void handlePointD(JoinPoint joinPoint) {
        System.out.println(Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * @param obj 返回值
     */
    @AfterReturning(pointcut = "pointCutA()", returning = "obj")
    public void handlePointDA(Object obj) {}

    /**
     * @param ex 抛出的异常
     */
    @AfterThrowing(pointcut = "pointCutA()", throwing = "ex")
    public void handleAfterThrowEx(Exception ex) {}

    @After("pointCutA()")
    public void handleAfter() {}

    @Around("pointCutA()")
    public void handleAround(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed();
    }

}
