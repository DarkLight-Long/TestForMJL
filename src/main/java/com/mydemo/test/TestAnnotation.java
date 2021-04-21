package com.mydemo.test;

import org.springframework.core.annotation.AnnotationUtils;
import java.util.Arrays;

@TestForAnnotation(path = "index")
public class TestAnnotation {

    @org.junit.Test
    public void getAnnotation() {
        TestForAnnotation annotation = AnnotationUtils.getAnnotation(getClass(), TestForAnnotation.class);
        System.out.println(annotation.index());
        System.out.println(annotation.value());  // 获取到了
        System.out.println(annotation.path());

        TestForAnnotation annotation2 = getClass().getAnnotation(TestForAnnotation.class);
        System.out.println(annotation2.index());
        System.out.println(annotation2.value()); //获取不到
        System.out.println(annotation.path());

        System.out.println(Arrays.toString(getClass().getAnnotationsByType(TestForAnnotation.class)));// 获取所有参数

    }

}
