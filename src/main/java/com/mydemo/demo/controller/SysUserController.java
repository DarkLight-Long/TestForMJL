package com.mydemo.demo.controller;

import com.mydemo.demo.domain.SysUser;
import com.mydemo.demo.service.ISysUserService.ISysUserService;
import org.flowable.idm.api.User;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.flowable.ui.common.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sys")
public class SysUserController {

    private static final String prefix = "/static";

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

    @GetMapping(value = "/index")
    public String index(){
//        User user1 = new UserEntityImpl();
//        user1.setId("admin");
//        user1.setFirstName("Test");
//        user1.setLastName("Administrator");
//        user1.setEmail("admin@flowable.org");
//        SecurityUtils.assumeUser(user1);
        return prefix + "/index";
    }

//    @GetMapping(value = "/auth")
//    public void auth(){
//        User user1 = new UserEntityImpl();
//        user1.setId("admin");
//        user1.setFirstName("Test");
//        user1.setLastName("Administrator");
//        user1.setEmail("admin@flowable.org");
//        SecurityUtils.assumeUser(user1);
//    }
}
