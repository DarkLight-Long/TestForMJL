package com.mydemo.demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class SysUser {
    @Id
    private String id;
    private String userName;
    private String password;
    private String account;
}
