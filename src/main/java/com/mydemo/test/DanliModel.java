package com.mydemo.test;

import java.util.Objects;

public final class DanliModel {

    private static DanliModel danliModel;

    private String name;
    private String address;
    private String idCard;
    private String phone;
    private String account;

    private DanliModel() {}

    public static DanliModel getInstance(String name, String address, String idCard, String phone, String account) {
        if (Objects.isNull(danliModel)) {
            danliModel = new DanliModel();
            danliModel.name = name;
            danliModel.address = address;
            danliModel.idCard = idCard;
            danliModel.phone = phone;
            danliModel.account = account;
        }
        return danliModel;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getPhone() {
        return phone;
    }

    public String getAccount() {
        return account;
    }

    public static void main(String[] args) {
        DanliModel danliModel = DanliModel.getInstance("张三", "233", "420", "133", "zhang");
        System.out.println(danliModel);
        DanliModel danliModel1 = DanliModel.getInstance("李四", "344", "420", "138", "li");
        System.out.println(danliModel1);
    }
}
