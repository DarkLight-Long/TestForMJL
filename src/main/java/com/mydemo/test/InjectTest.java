package com.mydemo.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class InjectTest {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class clazz = Class.forName("com.mydemo.test.InjectTest.Test");
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
//        method.setAccessible(true);   //如果为私有则需要带上
        method.invoke(obj);
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
