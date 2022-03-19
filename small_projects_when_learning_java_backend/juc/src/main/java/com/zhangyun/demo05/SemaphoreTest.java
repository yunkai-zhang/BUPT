package com.zhangyun.demo05;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
    public static void main(String[] args) {

        // 构造方法中传入“线程数量”；可以把它想象成停车场有3个停车位
        Semaphore semaphore = new Semaphore(3);

        //有6个车要进停车场
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    // acquire() 得到一个车位
                    semaphore.acquire();
                    //必须先抢到停车位，再打印，不然可能自己打印了，但是还没来得及实际抢到，就被别的线程实际抢到了停车位，就造成停的车与控制台打印不符
                    System.out.println(Thread.currentThread().getName() + "抢到车位");

                    //让本线程阻塞2秒，模拟在停车场停着；同时这2s可以让三辆车都进入停车库
                    TimeUnit.SECONDS.sleep(5);
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    //必须先打印再释放，否则可能释放完线程被别的线程抢到了，发生：一辆车还没离开另一辆车就进来，导致停车场车数超过额定数的打印情况
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                    // release() 释放掉一个停车位，即驶离停车场
                    semaphore.release();

                }
            },String.valueOf(i)).start();
        }
    }
}
