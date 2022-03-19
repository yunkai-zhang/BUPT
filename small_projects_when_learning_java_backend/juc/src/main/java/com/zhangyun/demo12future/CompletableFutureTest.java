package com.zhangyun.demo12future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest {
    public static void main(String[] args) throws Exception {
        //让一个线程异步执行脏活累活
        //因为无返回值，尖括号中要填Void，注意是首字母大写的Void类
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                //sleep模拟执行脏活累活需要的时间
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //用输出模拟干完了脏活
            System.out.println(Thread.currentThread().getName() + "runAsync=>Void");
        });

        //主线程在外包脏活后，dosomethingelse
        System.out.println("1111");

        //主线程现在想要脏活的结果了，就用get去拿
        completableFuture.get();
    }
}
