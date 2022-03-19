package com.zhangyun.demo08pool;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class DiyPool4 {
    public static void main(String[] args) {
        // 获取cpu 的处理器数目；比如6核12线程的cpu就有12个处理器(机)
        int max = Runtime.getRuntime().availableProcessors();

        //自定义线程池，在工作中只会用这个方式；因为用 Executors的三大方法去创建线程池不安全，且阿里巴巴规范不建议；下面7个参数需要烂熟于胸
        ExecutorService service =new ThreadPoolExecutor(
                2,
                max,//让线程池允许的最大线程数为cpu的处理机数目
                3,
                TimeUnit.SECONDS,//过期不候的时间单位为秒
                new LinkedBlockingDeque<>(3),//阻塞队列的构造函数传入3，表示最多允许三个线程在等待
                Executors.defaultThreadFactory(),//用默认的线程工厂即可
                new ThreadPoolExecutor.DiscardOldestPolicy()//接口RejectExecutionHandler有四个实现类，即有四种拒绝策略；这里使用DiscardOldestPolicy拒绝策略，队列满了，将最早进入队列的任务删除，之后再尝试加入队列，且不会抛出异常
        );

        try {
            //最大允许的任务数：阻塞队列大小+最大线程池大小
            for (int i = 1; i <= 16; i++) {
                int finalI=i;
                //自己创建了线程池后，使用线程池来创建线程
                service.execute(() -> {

                    //所有线程休眠16s，保证在最后一个被拒绝的任务需要加入队列的时候，处理机和阻塞队列都是满的
                    try {
                        sleep(16000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "处理第<"+finalI+">个任务 ok");
                });

                //每个线程之间睡眠1s，保证任务按顺序进入队列被处理
                sleep(1000);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            service.shutdown();
        }
    }
}

