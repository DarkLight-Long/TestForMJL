package com.mydemo.demo.service;

import com.mydemo.demo.domain.SysUser;

import java.util.List;

public interface SysUserService {
    public List<SysUser> selectAll();

    public void addUser();
}
