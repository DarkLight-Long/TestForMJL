package com.mydemo.demo.service.ImplSysUserService;

import com.mydemo.demo.dao.SysUserDao;
import com.mydemo.demo.domain.SysUser;
import com.mydemo.demo.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ISysUserService implements SysUserService{

    private final Logger logger = LoggerFactory.getLogger(ISysUserService.class);

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

    @Transactional
    public void insert() {
        SysUser sysUser = new SysUser();
        sysUser.setUserName("test");
        sysUser.setPassword("123456");
        sysUser.setAccount("asdasdas");
        sysUserDao.insert(sysUser);
    }
}
