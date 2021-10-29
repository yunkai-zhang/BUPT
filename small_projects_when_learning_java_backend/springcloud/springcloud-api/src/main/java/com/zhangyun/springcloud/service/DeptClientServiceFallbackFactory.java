package com.zhangyun.springcloud.service;

import com.zhangyun.springcloud.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/*
* DeptClientService()服务失败时通过create函数返回自定义的降级版的DeptClientService()
*
* 熔断和降级都写在api里确实容易让人误解，熔断是在服务提供者里用的，降级是在服务消费者里用的。
* 比如或者说再讲个服务降级的例子，你去售楼处买房子你是VIP客户，他就分配一个高级客户经理给你介绍，一看是个老头又没钱了他就找个实习生来给你讲解。
*
* 熔断就是对微服务中的一个方法配置回调函数，降级是对整个微服务配置回调函数。
* */
//@Component把DeptClientServiceFallbackFactory装到容器中
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory {
    @Override
    public DeptClientService create(Throwable throwable) {
        //使用了匿名内部类，因为接口不能返回，必须用匿名内部类实现接口的方法再返回。
        return new DeptClientService() {
            @Override
            public boolean addDept(Dept dept) {
                return false;
            }

            @Override
            public Dept queryById(long id) {
                return new Dept()
                        .setDeptno(id)
                        .setDname("id=>"+id+"没有对应的信息，客户端提供了降级的信息，这个服务现在已经被关闭")
                        .setDb_source("没有数据");
            }

            @Override
            public List<Dept> queryAll() {
                return null;
            }
        };
    }
}
