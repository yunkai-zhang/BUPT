package com.zhangyun.springcloud.controller;


import com.zhangyun.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//@RestController标记的类是一个控制器。分发处理器将会扫描使用了该注解的类的方法,且不走视图解析器。
@RestController
public class DeptConsumerController {
    //使用ribbon做负载均衡后，这里的地址应该是一个变量，通过服务名来访问。
    //private static final String REST_URL_PREFIX="http://localhost:8001";
    private static final String REST_URL_PREFIX="http://SPRINGCLOUD-PROVIDER-DEPT";

    /*理解：消费者，不应该有service层
    *
    * 前面就说了啊，Springcloud基于Http的Rest风格来进行通信。一共两种通信方式，一是RPC，二是http。
    * 我们这里使用RestTemplate，，很多方法供我们直接调用即可。不过我们要把RestTemplate注册到spring中。
    * 因为RestTemplate中没有@Bean，所以我们需要手动把它注册到spring中。
    *
    * 拿到resttemplate后，没有service层我们也能调用远程的服务。resttemplate提供多种便捷访问远程http服务的方法，
    * 他是一种简单的restful服务模板。
    * */
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("consumer/dept/getDeptById/{id}")
    public Dept getDeptById(@PathVariable("id") long id){
        /*
        * 通过resttemplate访问远程服务端提供的服务
        *
        * getForObject()表示用get方法从远程服务器拿回一个对象
        * 参数类型一般为：
        * 1. url；
        * 2. 实体（携带的参数们）：类型可以为Map，携带参数为int等基本数据类型时可以不用额外的实体而像本例直接把参数用restful风格融入url，
        * RestTemplate也有对应的重写的getForObject(URI url, Class<T> responseType)；
        * 3. Class<T>responseType
        *
        * restful不像rpc那要要求reference引用，restful直接通过http的url去请求，http-restful通过restTemplate实现。
        * 客户端和服务无关，没有service层，完全解耦。
        * */
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/qbid/"+id,Dept.class);
    }

    @RequestMapping("consumer/dept/getDeptAll")
    public List<Dept> getDeptAll(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/qall",List.class);
    }

    @RequestMapping("consumer/dept/addDept")
    public boolean addDept(Dept dept){
        /*
        * restTemplate.postForObject(URI url, @Nullable Object request, Class<T> responseType)参数解析:
        * 1. 请求远程服务需要的url
        * 2. 以post方式提交请求时需要携带的（对象）参数
        * 3. 控制器函数addDept()返回类型，需要和远程服务的返回值一致
        * */
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add/",dept,Boolean.class);
    }


}
