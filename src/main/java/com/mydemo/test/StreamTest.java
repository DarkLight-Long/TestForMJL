package com.mydemo.test;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 *
 */
public class StreamTest {

    // Predicate 有参, 返回boolean
    public static void testPredicate(Predicate<String> predicate) {
        Objects.requireNonNull(predicate);
        if (predicate.test("a")) {
            System.out.println("test");
        }
    }

    // Supplier 无参, 返回T
    public static void testSupplier(Supplier<String> supplier) {
        Objects.requireNonNull(supplier);
        System.out.println(supplier.get());
    }

    public static void main(String[] args) {
        testPredicate((item) -> {
            Objects.requireNonNull(item);
            return "a".equals(item);
        });

        testSupplier(() -> {
            return "a";
        });
    }

}
