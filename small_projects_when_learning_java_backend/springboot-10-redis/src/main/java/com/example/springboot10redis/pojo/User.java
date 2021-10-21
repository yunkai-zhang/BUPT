package com.example.springboot10redis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
//使用lombok的三个注解，这样只需要在pojo类中写属性
@AllArgsConstructor
@NoArgsConstructor
@Data
//企业中pojo类要实现序列化。implements Serializable
public class User implements Serializable{
    private String name;
    private int age;

}
