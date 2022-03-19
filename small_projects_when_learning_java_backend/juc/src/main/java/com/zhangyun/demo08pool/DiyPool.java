package com.zhangyun.demo08pool;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class DiyPool {
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
                new ThreadPoolExecutor.AbortPolicy()//接口RejectExecutionHandler有四个实现类，即有四种拒绝策略；这里使用AbortPolicy策略，即阻塞队列满了又有任务进来，就不处理该任务并抛出异常
        );

        try {
            //最大允许的任务数：阻塞队列大小+最大线程池大小
            for (int i = 1; i <= 16; i++) {
                //自己创建了线程池后，使用线程池来创建线程
                service.execute(() -> {

                    //所有线程休眠1s，省的一些线程还没启动，就有一些线程一瞬间就执行完了，导致无法看到抛出AbortPolicy异常
                    try {
                        sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "ok");
                });
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            service.shutdown();
        }
    }
}
