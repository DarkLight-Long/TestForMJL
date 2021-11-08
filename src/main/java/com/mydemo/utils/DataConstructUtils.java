package com.mydemo.utils;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class DataConstructUtils {

    public static void main(String[] args) {
        File dir = new File("/Users/xiaolan/Desktop/成理2022暑期课纲");
        StringBuilder builder = new StringBuilder();
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            Arrays.stream(files).sorted(Comparator.comparing(File::getName)).filter(file -> file.getName().contains(".pdf")).forEach(file -> {
                String name = file.getName().trim();
                String nameCode = name.split("-")[0].trim();
                builder.append(nameCode);
                builder.append(",");
                nameCode = nameCode.split(" ")[0] + nameCode.split(" ")[1];

                String nameName = name.split("-")[1].trim();
                nameName = nameName.trim().split("\\.")[0];
                builder.append(nameCode);
                builder.append(",");
                builder.append(nameName);
                builder.append(";\n");
            });
        }
        System.out.println(builder);
    }

}
