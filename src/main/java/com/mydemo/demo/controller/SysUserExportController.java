package com.mydemo.demo.controller;

import com.mydemo.demo.domain.SysUser;
import com.mydemo.demo.service.ISysUserService.ISysUserService;
import com.mydemo.demo.service.SysUserService;
import com.mydemo.demo.utils.ExportOrImportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("sysExport")
public class SysUserExportController {

    @Autowired
    private ISysUserService sysUserService;

    @GetMapping(value = "/export")
    @ResponseBody
    public void sysExport(HttpServletResponse response) {
        List<SysUser> sysUsers = sysUserService.selectAll();
        ExportOrImportUtil.export(response,sysUsers,"用户信息表",SysUser.class);
    }
}
