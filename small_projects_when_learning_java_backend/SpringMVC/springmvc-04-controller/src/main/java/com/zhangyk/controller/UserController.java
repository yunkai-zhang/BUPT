package com.zhangyk.controller;

import com.zhangyk.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    /*接收前端参数的内部流程：
     * 1. 接收前端用户传递的参数，判断参数的名字，假设名字直接在方法上，可以直接使用
     * 2. 假设传递的是一个对象User（通过方法的参数已知为对象），匹配User对象中的字段名：如果名字一致则ok，否则匹配不到
     * */

    //从前端接收一个参数：http://localhost:8080/user/t1?username=zhangyk
    @GetMapping("/t1")
    /*
    * 如果url中域名称和处理方法的参数名不一致，那么就应该用@RequestParam("域名称")来绑定域名称和处理方法的参数
    *
    * 如果url中域名称和处理方法的参数名一致，那么系统自动识别参数并绑定。但是要从前端接收参数时，
    * 推荐总是使用@RequestParam("username")注解，因为这会提醒程序员，要从前端接收该参数；
    * 否则程序员看后端代码不容易看出来方法参数到底要不要从前端接收。
    *
    * 其实用restful风格也可以避免所谓“url中域名称和处理方法的参数名不一致”的问题
    * */
    public String test1(@RequestParam("username") String name, Model model){
        /*1. 接收前端参数。通过String name可以接收。比如前段get方法传参：//localhost:8080/user/t1?name=xxx;
        * 不需要像servlet中用request.getparam()拿前端参数了，这就是SpringMVC的优秀之处（跨框架的魅力）
        * */
        System.out.println("接收到前端的参数为："+name);

        /*2. 将返回的结果传回前段，用model
        * */
        model.addAttribute("msg","name:"+name);

        /*3. 跳转视图
        * */
        return "hello";
    }

    //从前端接收到的是一个对象：id，name，age;http://localhost:8080/user/t2?name=zhangyk&id=1&age=22
    @GetMapping("/t2")
    /*如果接收的是一个对象的话，不需要用最笨的方法即在方法的参数区写上一个个的参数，直接写被传的对象即可
    * 不过这里要保证域名和对象中的参数名严格一致，否则接收不到名字不匹配的参数
    * */
    public String test2(User user, Model model){
        //1. 从前端接收到参数，在控制台打印
        System.out.println("接收到前端的参数为："+user);

        //2. 将返回的结果传回前段，用model
        model.addAttribute("msg","name:"+user.toString());

        //3. 跳转视图
        return "hello";
    }

    /*modelMap
    *
    * 给前端返回可以用ModelAndView（接口实现Controller中用过），Model（注解实现Controller中用过），还可以用ModelMap
    *
    * 继承上：linkedhashmap-》modelMap-》model
    * linkedhashmap
    * modelMap：继承了linkedhashmap，所以他拥有linkedhashmap的全部功能
    * model：精简版（大部分情况，我们都直接用model）
    *
    * 具体代码就不写了
    * */




}
