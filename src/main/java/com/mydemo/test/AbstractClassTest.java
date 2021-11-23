package com.mydemo.test;

public abstract class AbstractClassTest {

    AbstractClassTest() {
        System.out.println("");
    }

    public abstract void testA();

    public void testC() {

    }

    public static void main(String[] args) {
    }
}

class NormalClass extends AbstractClassTest {

    @Override
    public void testA() {

    }
}
