package com.mydemo.demo.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class SysUser {
    @Id
    @Excel(name = "id")
    private String id;
    @Excel(name = "用户名")
    private String userName;
    @ExcelIgnore
    private String password;
    @Excel(name = "账号")
    private String account;
}
