package com.mydemo.demo.dao;

import com.mydemo.demo.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface SysUserDao {
    public List<SysUser> selectAll(@Param("account") String account);

}
