package com.zhangyk.controller;
/*
* controller文件夹代表Servlet层，也叫web层。
* */

import org.springframework.stereotype.Controller;

/*
* 由于xml中配置了<context:component-scan base-package="com.zhangyk"/>，所以idea中可以看到类左边有个叶子，说明类对应的bean
* 对象被成功注册到Spring容器中。
* */
@Controller
public class UserController {
}
