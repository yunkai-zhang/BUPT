package com.zhangyk.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*
* 等价于<bean id="user" class="com.zhangyk.pojo.User"/>
* Component英文就是组件
* 注册bean的beanid默认为类名首字母小写
* */
@Component
//设置bean为单例模式，即全局该User类只有一个bean对象：user
@Scope("singleton")
public class User {

    //相当于之前xml配置登记bean时的<property name="name" value="zhangyk"/>
    @Value("张云")
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
