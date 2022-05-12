package com.zhangyun.designPattern;

public class Singleton {
    //static保证一个类只有一个singleton变量，volatile禁止指令重排保证线程安全，private防止外界直接访问singleton
    private volatile static Singleton singleton;
    //构造函数设置为private，进制外界直接构造。本构造函数只会通过getSingleton执行一次
    private Singleton(){}

    //获取单例，使用线程安全的双重校验锁。用public修饰，让外界能使用本方法。用static是为了让本方法全类只有一个。
    public static Singleton getSingleton(){
        //第一次判断为空，是为了避免在不需要加锁创建单例的时候加锁，因为加锁是消耗资源的。单例已经被创建的话，直接返回单例。
        if(singleton==null){
            //把Singleton.class当做锁来加锁，保证多线程下，只有一个线程能执行new Singleton(){};
            synchronized(Singleton.class){
                //第二次判断为空，是为了防止有些线程进入if(singleton==null){后，创建单例的线程才拿到锁，导致单例被创建两次
                if(singleton==null){
                    singleton=new Singleton(){};
                }
            }
        }

        return singleton;
    }
}
