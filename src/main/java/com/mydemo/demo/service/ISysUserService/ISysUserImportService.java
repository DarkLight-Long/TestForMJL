package com.mydemo.demo.service.ISysUserService;

import com.mydemo.demo.dao.SysUserDao;
import com.mydemo.demo.domain.SysUser;
import com.mydemo.demo.service.SysUserImportService;
import com.mydemo.demo.utils.ExportOrImportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class ISysUserImportService implements SysUserImportService {

    private static final Logger logger = LoggerFactory.getLogger(ISysUserService.class);

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public String Import(MultipartFile file,Class<?> clazz) {
        if (file.isEmpty()) {
            logger.error("导入失败,导入内容为空");
            return "导入失败,导入内容为空";
        }
        try {
            List<SysUser> sysUsers = ExportOrImportUtil.Import(file, clazz);
            sysUserDao.addUser(sysUsers);
            return "导入成功";
        }catch (Exception e) {
            logger.error(e.toString(),"导入失败");
            return "导入失败";
        }
    }
}
