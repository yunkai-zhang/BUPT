package com.zhangyun.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

//最好用component，防止和dubbo的service注解混淆
@Service
//接口不能被放入容器，所以这里得是class
public class UserService {
    //想拿到provider提供的票,要去注册中心拿到服务

    /*
    * dubbo包下的@Reference实现远程引用的功能，功能类似原来的@Autowired.
    *
    * 远方的TicketService无法找到，那就在本地定义路径相同的接口名，服务不需要相同。
    * 故把provider-service中的TicketService.java拷贝到consumer-server中
    * */
    @Reference
    TicketService ticketService;

    public void buyTicket(){
        String ticket=ticketService.getTicket();
        System.out.println("在注册中心拿到=》"+ticket);
    }


}
