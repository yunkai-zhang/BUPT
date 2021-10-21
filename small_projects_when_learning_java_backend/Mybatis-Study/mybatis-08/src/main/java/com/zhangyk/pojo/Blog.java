package com.zhangyk.pojo;
//java中用util；不要用sqldate，sql串联不了时间
import lombok.Data;

import java.util.Date;

@Data
public class Blog {

    private String id;
    private String title;
    private String author;
    private Date createTime;//属性名和字段名不一致
    private int views;

}
