package com.mydemo.test;

public enum EnumModel {
    RED ("red", "1", "111"),
    BLUE ("blue", "2", "122"),
    PINK("pink", "3", "133");

    private final String name;
    private final String address;
    private final String phone;

    EnumModel(String name, String address, String phone) {
        this.name = phone;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }


    public static void main(String[] args) {
        System.out.println(EnumModel.RED.getName());
        System.out.println(EnumModel.BLUE.getName());
        System.out.println(EnumModel.PINK.getName());
    }
}
