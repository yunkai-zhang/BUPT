package com.zhangyun.springcloud.controller;


import com.zhangyun.springcloud.pojo.Dept;
import com.zhangyun.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//@RestController标记的类是一个控制器。分发处理器将会扫描使用了该注解的类的方法,且不走视图解析器。
@RestController
public class FeignDeptConsumerController {

    //使用ribbon做负载均衡后，这里的地址是一个变量，通过服务名来访问。但是现在用Feign，就用接口的方式（更像java）
    //private static final String REST_URL_PREFIX="http://SPRINGCLOUD-PROVIDER-DEPT";
    /*
    feign实现负载均衡的Controller，比ribbonfeign实现负载均衡的Controller（springcloud-consumer-dept-80中）清爽很多。

    1. 如果deptClientService爆红，那么把@Autowired换成@Resource即可。
    2. 关于=null，@Mapper 也没有自己写实现类，运行中会自动生成，这个应该也一样，可以自动注入，爆红不影响运行
    * */
    @Autowired
    private DeptClientService deptClientService=null;

    /*
    * 使用feign的话不需要RestTemplate
    * */
    //    @Autowired
    //    private RestTemplate restTemplate;

    @RequestMapping("fconsumer/dept/getDeptById/{id}")
    public Dept getDeptById(@PathVariable("id") long id){
        return this.deptClientService.queryById(id);
    }

    @RequestMapping("fconsumer/dept/getDeptAll")
    public List<Dept> getDeptAll(){
        return this.deptClientService.queryAll();
    }

    @RequestMapping("fconsumer/dept/addDept")
    public boolean addDept(Dept dept){
        return this.deptClientService.addDept(dept);
    }


}
