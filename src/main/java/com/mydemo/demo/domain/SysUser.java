package com.mydemo.demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class SysUser {
    @Id
    private String id;
    private String userName;
    private String account;
    private String password;

    private Integer age;
    private Integer sex;
    private String phone;
    private String address;

    private Date createTime;
    private Date updateTime;
}
