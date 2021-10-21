package com.zhangyk.controller;

import com.zhangyk.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
* 前后端分离的时候，后端就提供字符串即可，所以用@RestController。
*
* 不走视图解析器，纯天然得屏蔽了“跳页面”，所以方法那不用添加requestBody
* */
@RestController
public class AjaxController {

    @RequestMapping("/t1")
    public String test(){
        return "hello1";
    }

    /*往a1传一个请求，带上name参数。如果name是zhangyk，那么页面上写true，否则写false。
    这个函数为void，所以没有字符串返回，而是直接在请求它的前端页面上写。*/
    @RequestMapping("/a1")
    public void a1(String name, HttpServletResponse response) throws IOException {
        System.out.println("a1:param=>"+name);
        if("zhangyk".equals(name)){
            /*
            * response.getWriter().print搭配ajax的用法： https://www.cnblogs.com/zzw3014/p/11857097.html
            * */
            response.getWriter().print("true");
        }
        else{
            response.getWriter().print("false");
        }

    }

    /*
    *  a2请求做的东西：要给请求方返回一些对象。
    *
    * */
    @RequestMapping("/a2")
    public List<User> a2(){
        List<User> userList = new ArrayList<User>();

        //添加数据
        userList.add(new User("张云学java",1,"男"));
        userList.add(new User("张云学前端",1,"男"));
        userList.add(new User("张云学运维",1,"男"));

        //pom导入了json包，且不走视图的话，返回值自动被转换成json，返回给请求的页面
        return userList;
    }

    @RequestMapping("/a3")
    public String a3(String name,String pwd){
        String msg="";

        //防止空指针异常，因为一次“失去焦点”的事件对应“离开user栏”或“离开pwd栏”。传入的user和pwd必定总有一个为空
        if(name!=null){
            //admin应该在数据库中查，这里增加可mybatis就可实现
            if("admin".equals(name)){
                msg="ok";
            }else{
                msg="用户名不存在";
            }
        }

        //防止空指针异常：可能只输入密码，或者只输入用户名
        if(pwd!=null){
            //密码123456应该在数据库中查，这里增加可mybatis就可实现
            if("123456".equals(pwd)){
                msg="ok";
            }else{
                msg="密码错误";
            }
        }

        //本函数不能同时返回name和pwd的结果，他只能一个个返回。比如先离开user栏就返回user的验证结果。
        return msg;

    }
}
