package com.mydemo.test;

public class A {

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
        return "A{" +
                "a='" + a + '\'' +
                '}';
    }
}
