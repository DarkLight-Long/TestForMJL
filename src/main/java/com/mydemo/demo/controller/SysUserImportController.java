package com.mydemo.demo.controller;

import com.mydemo.demo.domain.SysUser;
import com.mydemo.demo.service.ISysUserService.ISysUserImportService;
import com.mydemo.demo.service.ISysUserService.ISysUserService;
import com.mydemo.demo.utils.ExportOrImportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("sysImport")
public class SysUserImportController {

    @Autowired
    private ISysUserImportService sysUserImportService;

    @PostMapping(value = "/import")
    @ResponseBody
    public String sysImport(MultipartFile file) {
        return sysUserImportService.Import(file, SysUser.class);
    }

}
