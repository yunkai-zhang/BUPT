package com.zhangyun.springboot09test.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

//spring托管一下，加@Service
@Service
public class AsyncService {
    /*
    * @Async告诉spring这是一个异步的任务,spring会自动为被注解的函数开启多线程。免得自己手动写多线程。
    *
    * 开启异步（spring自动多线程）后，hello()的休眠三秒不会影响其后别的任务的执行。
    * */
    @Async
    public void hello(){
        try {
            //线程会休眠三秒，前台会白屏3秒
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据正在处理");
    }
}
