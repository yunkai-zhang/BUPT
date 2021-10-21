package com.zhangyk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zhangyk.pojo.User;
import com.zhangyk.util.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* @RestController: 不走视图解析器，直接给前端返回字符串
* @Controller：会走视图解析器，再给前端返回字符串。
*
* 如果需要一部分方法走视图解析器，另一些方法不走视图解析器，
* 那么可以在类上使用Controller使所有方法走视图解析器，再在不需要走视图解析器的方法上添加@ResponseBody注解。
* 类上使用了@RestController的话，相当于所有方法返回json数据，那么方法上就不需要写@Responsebody了
*
* @controller和@RestController区别：https://blog.csdn.net/weixin_39816448/article/details/111207141
* 现在一般是前后端分离，推荐使用RestController来传递对象，而不是用Controller1来传递视图。
* */
@RestController
public class UserController {

    //测试返回一个对象
    /*浏览器url为j1时，访问json1（）方法。produces = "application/json;charset=utf-8"防止中文乱码
    @RequestMapping(value="/j1", produces = "application/json;charset=utf-8")
    *
    * 不过这样每个请求都要设置一下防乱码，麻烦。最好在SpringMVC配置文件中实现防乱码（已实现）
    * */
    @RequestMapping(value="/j1")
    //加了@ResponseBody，他就不会走视图解析器，会直接给请求该方法的前端返回一个字符串
    //    @ResponseBody
    public String json1() throws JsonProcessingException {

        //jackson, objectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        //创建一个对象
        User user=new User("张云1",3,"男");
        String str = objectMapper.writeValueAsString(user);

        return str;
    }

    //测试返回多个对象
    @RequestMapping(value="/j2")
    public String json2() throws JsonProcessingException {

        //jackson, objectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> userList =new ArrayList<User>();

        //创建一个对象
        User user1=new User("张云1",3,"男");
        User user2=new User("张云2",3,"男");
        User user3=new User("张云3",3,"男");
        User user4=new User("张云4",3,"男");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        String str = objectMapper.writeValueAsString(userList);

        return str;
    }

    //输出一个时间
    @RequestMapping(value="/j3")
    public String json3() throws JsonProcessingException {

        //jackson, objectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        //一般date都是导入util包下的那个
        Date date = new Date();

        /*objectMapper，时间解析date后的默认格式为：timestamp，时间戳，即从1970/1/1到现在的毫秒数
        *
        * 可以用SimpleDateFormat把date变成人看得懂的形式:
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        不过最好用ObjectMapper自己的配置来实现时间显示
        * */

        //使用objectMapper来格式化输出:不使用时间戳的方式
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS,false);
        //自定义日期的格式，这还是需要simpledateformat
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //把定义好的时间格式设置为mapper的时间格式
        objectMapper.setDateFormat(simpleDateFormat);

        String str = objectMapper.writeValueAsString(date);

        return str;
    }

    //使用自定义工具类实现json3()，实现代码复用
    @RequestMapping(value="/j4")
    public String json4() throws JsonProcessingException {
        return JsonUtils.getIsonDate();
    }

    //测试fastjson
    @RequestMapping(value="/j5")
    public String json5() throws JsonProcessingException {

        //创建一个对象
        List<User> userList =new ArrayList<User>();
        User user1=new User("张云1",3,"男");
        User user2=new User("张云2",3,"男");
        User user3=new User("张云3",3,"男");
        User user4=new User("张云4",3,"男");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        System.out.println("*******Java对象 转 JSON字符串*******");
        String str1 = JSON.toJSONString(userList);
        System.out.println("测试数组：JSON.toJSONString(list)==>"+str1);
        String str2 = JSON.toJSONString(user1);
        System.out.println("测试单个对象：JSON.toJSONString(user1)==>"+str2);

        System.out.println("\n****** JSON字符串 转 Java对象*******");
        User jp_user1=JSON.parseObject(str2,User.class);
        System.out.println("JSON.parseObject(str2,User.class)==>"+jp_user1);

        System.out.println("\n****** Java对象 转 JSON对象 ******");
        JSONObject jsonObject1 = (JSONObject) JSON.toJSON(user2);
        System.out.println("(JSONObject) JSON.toJSON(user2)==>"+jsonObject1.getString("name"));

        System.out.println("\n****** JSON对象 转 Java对象 ******");
        User to_java_user = JSON.toJavaObject(jsonObject1, User.class);
        System.out.println("JSON.toJavaObject(jsonObject1, User.class)==>"+to_java_user);

        return "json转化效果都在控制台输出了";
    }

}
