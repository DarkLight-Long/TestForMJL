package com.mydemo.test;

/**
 * description: 继承，静态数据，以及类加载
 * time: 2021.11.2
 */
public class ExtendTest {

    public static class A {
        public String a;
        {
            this.a = "a";
            System.out.println("default A");
        }
        public A() {
            System.out.println("init A");
        }
        public void out() {
            System.out.println("A");
        }
        @Override
        public String toString() {
            return "A{" + "a='" + a + '\'' + '}';
        }
    }

    public static class B extends A {
        {
            this.a = "b";
            System.out.println("default B");
        }
        public B() {
            System.out.println("init B");
        }
        public void out() {
            System.out.println("B");
        }
    }

    public static class C extends B {
        public static String a = "A";
        {
            this.a = "c";
            System.out.println("default C");
        }
        public C() {
            System.out.println("init C");
        }
        public void out() {
            System.out.println("C");
        }
    }

    public static void main(String[] args) {
        C c = new C();
        System.out.println( C.a);
    }

}
