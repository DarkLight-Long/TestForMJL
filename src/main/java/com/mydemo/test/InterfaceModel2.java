package com.mydemo.test;

public interface InterfaceModel2 extends InterfaceModel {

    public default void methodA(String a) {
        System.out.println("interface default method");
    }

    public default void methodB() {
        System.out.println("interface default method");
    }

    @Override
    public default void  methodC() {

    }

}
