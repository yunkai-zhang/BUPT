package com.zhangyun.demo02;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class LockCAP {
    public static void main(String[] args) {
        Data2 data = new Data2();

        //开启AC两个做加法的线程，开启BD两个做减法的线程
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

//Data2是资源类，用于处理数字
class Data2 {
    private int num = 0;
    //把之前的synchronized锁变成lock锁
    Lock lock = new ReentrantLock();
    //可以根据lock生成一个condition；根据官方文档，condition取代了对象监视器方法的使用！！！。
    Condition condition = lock.newCondition();

    // +1
    //Lock方式实现的话，资源类中的方法不需要synchronized了
    public  void increment() throws InterruptedException {
        /*
        * lock()这没抢到condition锁的，就会被阻塞，抢到的才能往下走。
        * synchronized方式中，加了synchronized关键字的方法的区域是同步区域；lock方式中，lock()和unlock之间是同步区域
        *
        * lock就是放在try外面的，因为lock不属于业务
        * */
        lock.lock();
        try {//try里面写业务代码
            // 判断等待
            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "=>" + num);
            // 通知其他线程 +1 执行完毕
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }

    // -1
    //Lock方式实现的话，资源类中的方法不需要synchronized了
    public  void decrement() throws InterruptedException {

        /*
         * lock()这没抢到condition锁的，就会被阻塞，抢到的才能往下走。
         * synchronized方式中，加了synchronized关键字的方法的区域是同步区域；lock方式中，lock()和unlock之间是同步区域
         *
         * lock就是放在try外面的，因为lock不属于业务
         * */
        lock.lock();
        try {//try里面写业务代码
            // 判断等待
            while (num == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "=>" + num);
            // 通知其他线程 +1 执行完毕
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }
}

