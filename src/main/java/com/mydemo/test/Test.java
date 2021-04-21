package com.mydemo.test;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Name> names = new ArrayList<>();
        for (int i =1; i<= 10; i++) {
            if (i % 2 == 0) {
                names.add(new Name("li"));
            } else {
                names.add(new Name("zhang"));
            }
        }

        boolean b = names.stream().noneMatch(name ->
                {
                    System.out.println(name.name);
                    return name.name.equals("li");
                }
        );
        System.out.println(b);
    }

    @Data
    static class Name {
        String name;

        Name (String name) {
            this.name = name;
        }
    }

}
