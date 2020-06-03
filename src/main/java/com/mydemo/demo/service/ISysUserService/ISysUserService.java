package com.mydemo.demo.service.ISysUserService;

import com.mydemo.demo.dao.SysUserDao;
import com.mydemo.demo.domain.SysUser;
import com.mydemo.demo.service.SysUserService;
import io.netty.util.internal.EmptyArrays;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ISysUserService implements SysUserService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public List<SysUser> selectAll() {
        List<SysUser> list = new ArrayList<>();
        list = sysUserDao.selectAll();
        logger.debug(list.toString());
        return list;
    }
}
