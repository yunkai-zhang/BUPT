package com.zhangyk.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//使用java配置Spring时，类上面不需要@component注解，因为该注解是通过纯注解配置时才用的
//虽然不需要@component，但是也可以加上。这里@component的意思就是说，这个类被Spring接管了，注册到容器中（？增加可读性，体现注解的“注”属性）
@Component
public class User {

    //通过java配置注册bean对象可以，但是属性的注入还是得通过注解
    @Value("张云")
    private String name;

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
