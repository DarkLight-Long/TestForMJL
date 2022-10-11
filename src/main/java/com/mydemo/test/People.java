package com.mydemo.test;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class People {

    public String name;
    public String sex;
    public String phone;

    private String address;
    private int height;
    private int weight;

    private People() {}

    public static People initMan() {
        People man = new People();
        man.sex = "男";
        return man;
    }

    public static People initWoman() {
        People woman = new People();
        woman.sex = "女";
        return woman;
    }

    public static void main(String[] args) {
        log.info(String.format("%s 进入应用", "me"));
    }

}
