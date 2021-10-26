package com.zhangyun.springcloud.service;

import com.zhangyun.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/*
* 写了这个注解后，就和RPC的refernece注解一样，可以对服务直接调用了。
*
* value填applicationname
* */
@FeignClient(value="SPRINGCLOUD-PROVIDER-DEPT")
public interface DeptClientService {

    /*方法和服务提供者的接口中的一致
    *
    * 请求路径和服务提供者controller的路径也得一致，不然报错！！！
    * */

    @PostMapping("/dept/add")
    public boolean addDept(Dept dept);

    //这里@pathcariable里面必须写上id要不然会出错
    @GetMapping("/dept/qbid/{id}")
    public Dept queryById(@PathVariable("id") long id);

    @GetMapping("/dept/qall")
    public List<Dept> queryAll();
}
