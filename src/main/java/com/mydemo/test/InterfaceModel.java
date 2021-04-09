package com.mydemo.test;

import java.sql.SQLOutput;

public interface InterfaceModel {

    public default void methodA() {
        System.out.println("interface default method");
    }

    public default void methodB() {
        System.out.println("interface default method");
    }

    public void methodC();


}
