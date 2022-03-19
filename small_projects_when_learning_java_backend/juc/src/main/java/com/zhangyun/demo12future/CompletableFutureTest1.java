package com.zhangyun.demo12future;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest1 {
    public static void main(String[] args) throws Exception {
        //因为有返回值，尖括号中要填返回值的类型；supplyAsync中传入一个实现了Supplier接口（之前“四大函数式接口讲过”）的lamda表达式
        CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName() + "supplyAsync=>Integer");
                    int i = 10 / 0;
                    return 1024;
                });

        //想获取另一个线程执行dirty work的结果
        //whenComplete中要传入一个实现了BiConsumer接口的lamda表达式；BiConsumer可以传递两个参数且无返回值，是Consumer的增强版
        System.out.println(completableFuture.whenComplete((t, u) -> {
            System.out.println("t=>" + t); // 正常的返回结果
            System.out.println("u=>" + u); // 错误信息：java.util.concurrent.CompletionException:java.lang.ArithmeticException: /byzero
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 233; // 可以返回“使用whenComplete获取目标任务结果”失败情况下的返回结果
        }).get());
    }
}
