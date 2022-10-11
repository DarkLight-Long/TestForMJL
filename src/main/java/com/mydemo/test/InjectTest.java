package com.mydemo.test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 反射
 */
public class InjectTest {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        // 类初始化时，会自动执行静态块和静态对象
        Class clazz = Class.forName("com.mydemo.test.InjectTest.Test");
        // 不会
        Class clabb = ClassLoader.getSystemClassLoader().loadClass("com.mydemo.test.InjectTest.Test");
        Object obj = clazz.newInstance();

        Field[] fields = clazz.getDeclaredFields();
        Arrays.stream(fields).forEach(System.out::println);

        Field field = clazz.getField("name");
//        field.setAccessible(true);   //如果为私有则需要带上
        field.set(obj, "张三");
        System.out.println(obj);

        Method[] methods = clazz.getDeclaredMethods();
        Arrays.stream(methods).forEach(System.out::println);

        Method method = clazz.getDeclaredMethod("out");
        method.setAccessible(true);   //对象是否覆盖语言级别的访问检查
        method.invoke(obj); // 划重点，动态代理就用的这里

        // 创建数组
        Object array = Array.newInstance(clazz, 2);
        Array.set(array, 0, new Test());
        Array.set(array, 1, new Test());
        System.out.println(Array.get(array, 0));
    }

    static class Test {
        public String name;
        public String phone;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

}
