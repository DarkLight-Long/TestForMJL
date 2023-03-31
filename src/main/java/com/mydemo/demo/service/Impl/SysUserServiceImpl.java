package com.mydemo.demo.service.Impl;

import com.mydemo.demo.dao.SysUserDao;
import com.mydemo.demo.domain.SysUser;
import com.mydemo.demo.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional()
public class SysUserServiceImpl implements SysUserService {

    private final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    /**
     * 开启事务的第二种方法，可以为不同事务传播类型设置不同的bean
     * Todo 为不同事务传播类型设置不同的bea
     */
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public List<SysUser> selectAll() {
        List<SysUser> list = new ArrayList<>();
        String account = "abc";
        SysUser sysUser = new SysUser();
        sysUser.setUserName("test");
        sysUser.setPassword("123456");
        sysUser.setAccount("aaaaaaaa");
        sysUserDao.insert(sysUser);
        insert();
        list = sysUserDao.selectAll(account);
        return list;
    }

    @Override
    public void addUser() {
        SysUser sysUser = new SysUser();
        sysUser.setUserName("test");
        sysUser.setPassword("123456");
        sysUser.setAccount("asdasdas");
        sysUserDao.insert(sysUser);
    }

    public void insert() {
        SysUser sysUser = new SysUser();
        sysUser.setUserName("test");
        sysUser.setPassword("123456");
        sysUser.setAccount("asdasdas");
        sysUserDao.insert(sysUser);
    }
}
