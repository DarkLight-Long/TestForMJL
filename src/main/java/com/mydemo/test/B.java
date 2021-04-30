package com.mydemo.test;

public class B extends A {

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
