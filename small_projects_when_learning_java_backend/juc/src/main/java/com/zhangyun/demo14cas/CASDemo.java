package com.zhangyun.demo14cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {

    public static void main(String[] args) {
        //原子类的底层用了CAS
        AtomicInteger atomicInteger = new AtomicInteger(2020);

        /*boolean compareAndSet(int expect, int update)，本函数在java层面实现“比较并设置”的功能
        * 参数为(期望值、更新值)
        * 如果实际值 和 我的期望值相同，那么就更新值
        * 如果实际值 和 我的期望值不同，那么就不更新值
        *
        * compareAndSet中用到了unsafe.compareAndSwapInt，这里的compareAndSwap即为CAS（比较并交换）；
        * unsafe.compareAndSwapInt在操作系统层面实现“比较并交换”的功能
        * CAS是CPU的并发原语，即CAS是CPU的指令
        * */
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());

        //因为期望值是2020  实际值却变成了2021  所以会修改失败
        atomicInteger.getAndIncrement(); //++操作
        System.out.println(atomicInteger.compareAndSet(2020, 2222));
        System.out.println(atomicInteger.get());
    }
}
