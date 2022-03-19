package com.zhangyun.demo05;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        /*
        集齐七颗龙珠召唤神龙
        */

        // 召唤神龙的线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {
            System.out.println("召唤神龙成功");
        });

        for (int i = 1; i <= 7; i++) {
            // 子线程
            final int finalI = i;
            new Thread(() -> {
                //lamda表达式中能否用到for循环中的i？lamda表达式本质是new了一个对象，不能在lamda表达式中拿到外层for循环的变量；想拿到得通过lamda表达式外的final变量来拿。
                System.out.println(Thread.currentThread().getName() + "收集了第" + finalI + "颗龙珠");



                try {
                    // 调用await时，cyclicBarrier内部会加1；等待cyclicBarrier的计数变为7，就会打印创建对象cyclicBarrier时定义的“召唤神龙成功”。如果cyclicBarrier永远到不了7，即for循环的次数太少，则程序无法结束，主线程卡在召唤神龙那里。
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}