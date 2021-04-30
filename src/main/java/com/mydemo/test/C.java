package com.mydemo.test;

public class C extends B {

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
