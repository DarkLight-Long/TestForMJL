package com.mydemo.demo.controller;

import com.mydemo.demo.domain.SysUser;
import com.mydemo.demo.service.ISysUserService.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sys")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @PostMapping(value = "/select")
    @ResponseBody
    public List<SysUser> select() {
        return sysUserService.selectAll();
    }

    @GetMapping(value = "/select")
    @ResponseBody
    public List<SysUser> select2() {
        return sysUserService.selectAll();
    }
}
