package com.zhangyun.dockercomposespringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountsController {
    //引入redis
    @Autowired
    RedisTemplate redisTemplate;

    //每读一次就浏览量加一,并返回浏览量给前端页面
    @RequestMapping("/vnc")
    public String viewNumCount(){
        Long viewNum = redisTemplate.opsForValue().increment("viewNum");
        return "你好张云，浏览量为："+viewNum;
    }
}
