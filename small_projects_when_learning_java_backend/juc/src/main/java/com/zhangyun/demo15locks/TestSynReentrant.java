package com.zhangyun.demo15locks;

import static java.lang.Thread.sleep;

public class TestSynReentrant {
    public static void main(String[] args) {
        //资源类的实例化对象
        Phone phone = new Phone();

        //启动线程A
        new Thread(()->{
            phone.sms();
        },"A").start();

        //主线程睡眠1s，保证线程A能抢到对象phone的锁，再启动线程B
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动线程B
        new Thread(()->{
            phone.sms();
        },"B").start();
    }

}

class Phone{
    public synchronized void sms(){
        System.out.println(Thread.currentThread().getName()+"=> sms");
        call();//这里也有一把锁;表示发完短信后必须打电话
    }
    public synchronized void call(){
        System.out.println(Thread.currentThread().getName()+"=> call");
    }
}
