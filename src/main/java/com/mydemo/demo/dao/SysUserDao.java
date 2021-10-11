package com.mydemo.demo.dao;

import com.mydemo.demo.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysUserDao {
    public List<SysUser> selectAll(@Param("account") String account);

    void insert(SysUser sysUser);

}
