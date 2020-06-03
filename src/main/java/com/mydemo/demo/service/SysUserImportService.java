package com.mydemo.demo.service;

import com.mydemo.demo.domain.SysUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SysUserImportService {

    public String Import(MultipartFile file,Class<?> clazz);
}
