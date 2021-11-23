package com.mydemo.demo.controller;

import com.mydemo.demo.domain.SysUser;
import com.mydemo.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/sys")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping(value = "/select")
    @ResponseBody
    public List<SysUser> select() {
        return sysUserService.selectAll();
    }

    @GetMapping(value = "/select")
    @ResponseBody
    public List<SysUser> select2(String name) throws Exception {
        System.out.println("name");
        // redis数据类型 String
        stringRedisTemplate.opsForValue().set("string", "string");

        // redis数据类型 GeoLocation
        Point point = new Point(20, 20);
        RedisGeoCommands.GeoLocation location = new RedisGeoCommands.GeoLocation(name, point);
        redisTemplate.opsForGeo().add("geolocation", location);

        // redis数据类型 hash
        redisTemplate.opsForHash().putIfAbsent("hash", "key", "value");

        // redis数据类型 list
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        redisTemplate.opsForList().set("list", list.size(), list);
        redisTemplate.opsForList().rightPush("list", "D");
        redisTemplate.opsForList().set("list2", list.size(), list);
        redisTemplate.opsForList().rightPopAndLeftPush("list1", "list2");

        // redis数据类型 set =>不可重复的list
        redisTemplate.opsForSet().add("set", 1,2,3);
        redisTemplate.opsForSet().add("set", 4,5,6);
        System.out.println(redisTemplate.opsForSet().pop("set"));
        System.out.println(redisTemplate.opsForSet().members("set"));

        // redis数据类型 zset =>带排序的set
        Set<String> set = new HashSet<>();
        set.add("A");
        set.add("B");
        set.add("A");
        redisTemplate.opsForZSet().add("zset", set);
        if (true) {
            throw new Exception("测试aop切面");
        }

        return sysUserService.selectAll();
    }
}
