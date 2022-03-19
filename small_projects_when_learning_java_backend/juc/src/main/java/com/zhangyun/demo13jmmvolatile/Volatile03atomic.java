package com.zhangyun.demo13jmmvolatile;

import java.util.concurrent.atomic.AtomicInteger;

public class Volatile03atomic {
    //多线程操作volatile修饰的普通int变量，不保证原子性；使用原子类保证多线程操作一个变量时，实现原子性
    private volatile static AtomicInteger number = new AtomicInteger();

    //这里add用synchronized修饰，可以让自建的20个线程都抢Volatile.class对象，保证add()的原子性;
    // 但synchronized不轻量，我们用尝试使用volatile修饰number来实现原子性；但是不能实现，因为volatile不保证原子性
    public static void add(){
        //++ 不是一个原子性操作，是两个~3个操作
        //number++;

        //使用AtomicInteger中的getAndIncrement方法，使AtomicInteger实例化对象的值加1
        //getAndIncrement不是简单的+1操作，它用的是底层的CAS，由于CPU的并发原因，使用CAS效率很高（远比Synchronized高）
        number.getAndIncrement();
    }

    public static void main(String[] args) {
        //理论上number == 20000
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000 ; j++) {
                    add();
                }
            }).start();
        }

        //java中有两个默认线程“main  gc”。
        // Thread.yield()是在主线程中执行的，意思只要还有除了GC和main线程之外的线程在跑，主线程就让出cpu不往下执行
        while (Thread.activeCount()>2){

            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+",num="+number);
    }
}
