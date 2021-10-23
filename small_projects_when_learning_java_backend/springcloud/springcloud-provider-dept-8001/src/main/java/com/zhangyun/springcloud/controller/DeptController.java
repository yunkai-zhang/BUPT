package com.zhangyun.springcloud.controller;

import com.zhangyun.springcloud.pojo.Dept;
import com.zhangyun.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//提供restful服务
//@RestController使返回字符串不走视图解析器，直接以字符串形式输出在浏览器上
@RestController
public class DeptController {
    //controller层调用service层,从spring容器中拿到bean。
    @Autowired
    private DeptService deptService;
    //获取一些配置的信息，得到具体的微服务。DiscoveryClient已通过依赖引入了项目。
    @Autowired
    private DiscoveryClient client;

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

    /*
    * 通过请求获取一些Eureka信息：
    * 1. 注册进EurekaServer的微服务。
    * 2. 获取一些本子module的application.yml中配置的Eureka消息（Info）。
    *
    * 和别人联合开发时能用上
    * */
    @GetMapping("/dept/discovery")
    public Object discovery(){
        //获取微服务列表的清单
        List<String> services = client.getServices();
        System.out.println("discovery=>service:"+services);

        //得到一个具体的微服务信息,通过applicationname来取；服务提供者的application.yml中配置了applicationname
        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost()+"\t"+
                    instance.getPort()+"\t"+
                    instance.getUri()+"\t"+
                    instance.getServiceId()+"\t"
            );
        }

        return this.client;

    }
}
