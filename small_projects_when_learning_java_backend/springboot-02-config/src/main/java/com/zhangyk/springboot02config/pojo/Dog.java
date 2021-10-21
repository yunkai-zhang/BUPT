package com.zhangyk.springboot02config.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
@Component注册bean，把被注解的类添加到spring组件中

注意，项目的文件和文件夹要放在启动类的同一层，springboot才能找到。否则会发现：
添加了component注解后类名的左边不出现叶子，即类没有被注册为bean
*/
@Component
public class Dog {
    @Value("旺财")
    private String name;
    @Value("2")
    private Integer age;

    public Dog() {
    }

    public Dog(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
