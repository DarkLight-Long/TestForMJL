package com.mydemo.demo.utils;

import com.alibaba.fastjson2.JSON;
import com.mydemo.test.ExtendTest;
import lombok.Data;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 检测Foreach循环中出现的数据库高并发高压力问题
 */
public class TestForForEach {
    @Test
    public void testForFor() {
        Integer[] arr = new Integer[]{1,2,3,4,5,6,7,8,9};
        for (int i=0;i<arr.length;i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
    @Test
    public void testForFor2() {
        Integer[] arr = new Integer[]{1,2,3,4,5,6,7,8,9};
        for (Integer i : arr) {
            System.out.println(Thread.currentThread().getName());
        }
    }
    @Test
    public void testForArrayForEach() {
        Integer[] arr = new Integer[]{1,2,3,4,5,6,7,8,9};
        Arrays.stream(arr).forEach(item -> {
            System.out.println(Thread.currentThread().getName());
        });
    }
    @Test
    public void testForListForEach() {
        List<String> strings = new ArrayList<>();

    }
}
