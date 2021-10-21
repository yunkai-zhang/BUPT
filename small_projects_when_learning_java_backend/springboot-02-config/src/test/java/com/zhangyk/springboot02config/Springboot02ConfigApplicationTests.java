package com.zhangyk.springboot02config;

import com.zhangyk.springboot02config.pojo.Dog;
import com.zhangyk.springboot02config.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot02ConfigApplicationTests {

    @Autowired
    private Dog dog;
    //对每个对象都要用Autowired，我刚以为一个autowired可以给所有对象自动注入，结果yaml的内容无法注入person，查半天
    @Autowired
    private Person person;

    @Test
    void contextLoads() {

        System.out.println("打印通过注解设置值的dog");
        System.out.println(dog);

        System.out.println("打印通过yaml设置值的person");
        System.out.println(person);
    }

}
