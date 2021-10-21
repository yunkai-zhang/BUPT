package com.zhangyun.springcloud.controller;

import com.zhangyun.springcloud.pojo.Dept;
import com.zhangyun.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//提供restful服务
//@RestController使返回字符串不走视图解析器，直接以字符串形式输出在浏览器上
@RestController
public class DeptController {
    //controller层调用service层,从spring容器中拿到bean。
    @Autowired
    private DeptService deptService;

    //提交带对象参数请求一般都用post提交，安全一点
    @PostMapping("/dept/add")
    //restful的对象参数前面要用@RequestBody，否则可能restful传输的时候出问题
    public boolean addDept(@RequestBody Dept dept){
        return deptService.addDept(dept);
    }
    //restful风格携带参数
    @GetMapping("/dept/qbid/{id}")
    public Dept queryById(@PathVariable("id") long id){
        return deptService.queryById(id);
    }

    @GetMapping("/dept/qall")
    public List<Dept> queryAll(){
        return deptService.queryAll();
    }
}
