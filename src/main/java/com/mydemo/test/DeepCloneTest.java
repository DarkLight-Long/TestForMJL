package com.mydemo.test;

import lombok.Data;

/**
 * @author 1
 * @description 深拷贝
 */
public class DeepCloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        A a = new A();
        a.a = "A";
        a.b = new B();
        a.b.c = "A";

        A b = a.clone();
        System.out.println(a + a.a + a.b + a.b.c);
        System.out.println(b + b.a + b.b + b.b.c);
    }

//    @Data
    static class A implements Cloneable {
        private B b;
        private String a;

        @Override
        protected A clone() throws CloneNotSupportedException {
            A clone = (A)super.clone();
            clone.b = b.clone();
            return clone;
        }
    }

//    @Data
    static class B implements Cloneable {
        private String c;

    @Override
    protected B clone() throws CloneNotSupportedException {
        return (B) super.clone();
    }
}

}
