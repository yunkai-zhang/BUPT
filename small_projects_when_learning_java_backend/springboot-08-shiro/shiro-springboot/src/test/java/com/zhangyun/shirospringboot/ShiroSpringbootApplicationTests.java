package com.zhangyun.shirospringboot;

import com.zhangyun.shirospringboot.service.UserService;
import com.zhangyun.shirospringboot.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpringbootApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        System.out.println(userService.queryUserByName("张云"));
    }

}
