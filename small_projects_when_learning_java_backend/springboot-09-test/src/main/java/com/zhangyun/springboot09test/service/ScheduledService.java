package com.zhangyun.springboot09test.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
    /*
    * 在一个特定时间执行这个方法-Timer
    *
    * cron表达式：秒 分 时 日 月 周几。参数以空格隔开。
    * 30 15 10 * * ？  每天10点15分30秒执行一次。问号表示不需要关注该项。
    * 30 0/5 10,18 * * ?   每天10点和18点，每隔5分钟（0/5表示取模）的第30秒执行一次
    * 0 15 10 ？ * 1-6  每个月的周一到周六10点15分的第0秒执行一次
    * 下面@Scheduled()中参数为cron表达式:“每周每一天”的任意“月”“日”“时”“分”的第0秒会执行被注解的函数。
    *
    * */
    @Scheduled(cron="0 * * * * 0-7")
    public void hello(){
        System.out.println("hello，你被执行了");
    }
}
