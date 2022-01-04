package com.zhangyun.redis02springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

//把User类变成一个组件
@Component
//使用lombok，简化pojo类的编写（其实不推荐使用lombok，不过这用一下）
@AllArgsConstructor
@NoArgsConstructor
@Data
//!!!implements Serializable使User类能够被序列化
public class User implements Serializable {
    private String name;
    private int age;
}
