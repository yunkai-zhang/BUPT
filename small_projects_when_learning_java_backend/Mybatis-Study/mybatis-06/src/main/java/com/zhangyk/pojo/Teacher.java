package com.zhangyk.pojo;

import lombok.Data;

//使用lombok免去生成getset等语句
@Data
public class Teacher {

    private int id;
    private String name;
}
