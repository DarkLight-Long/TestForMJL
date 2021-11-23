package com.mydemo.test;

public interface InterfaceModel {

    /**
     * default is only allowed with an interface
     */
    public default void methodA() {
        System.out.println("interface default method");
    }

    public default void methodB() {
        System.out.println("interface default method");
    }

    public void methodC();
}
