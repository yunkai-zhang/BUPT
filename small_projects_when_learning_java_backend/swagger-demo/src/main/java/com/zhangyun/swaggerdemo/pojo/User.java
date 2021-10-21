package com.zhangyun.swaggerdemo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//@ApiModel给实体类加文档注释
@ApiModel("用户实体类")
public class User {
    //属性private也可以在swagger的model中展示，但是要加上get set才能在swager中展示属性
    @ApiModelProperty("用户名")
    public String username;
    //@ApiModelProperty给实体类的字段加注释
    @ApiModelProperty("密码")
    public String password;
}
