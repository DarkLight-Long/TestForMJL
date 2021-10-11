package com.mydemo.test;

public class TestForStaticContent {
    /**
     * 加载先后顺序：
     *  静态参数，静态加载方法,类加载方法,类实例化
     * 注意事项
     *  1. 静态相关只加载一次
     *  2. 静态数据和方法调用时不会实例化类
     */

    public static void main(String[] args) {
        StaticContent staticContent = new StaticContent();
        StaticContent staticContent1 = new StaticContent();
//        System.out.println(StaticContent.b);
//        StaticContent.test();
    }

    static class StaticContent {
        String a;
        static String b = "a";

        {
            System.out.println("加载");
            System.out.println(a);
        }
        static {
            System.out.println("静态方法加载");
            System.out.println(b);
        }

        StaticContent() {
            System.out.println("初始化");
        }

        static void test() {
            System.out.println("静态方法");
        }
    }

}
