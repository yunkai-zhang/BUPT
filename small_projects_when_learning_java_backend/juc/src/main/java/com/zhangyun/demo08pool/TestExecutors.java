package com.zhangyun.demo08pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecutors {

    public static void main(String[] args) {

//        test1();
//        test2();
        test3();

    }

    //使用“只包含单个线程的线程池”
    public static void test1(){
        //Executors是一个工具类，该工具类包含 三大方法；
        ExecutorService threadPool = Executors.newSingleThreadExecutor();//只包含单个线程的线程池

        try {
            for (int i = 1; i <= 10; i++) {
                /*
                 * 传统新建线程是new Thread.start()，但是现在是用线程池创建线程
                 * */
                //通过线程池创建线程，在execute方法中丢一个Runnable(具体的线程)进去
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池用完，程序结束，必须要关闭线程池
            threadPool.shutdown();
        }
    }

    //使用“内含线程数的大小固定的线程池”
    public static void test2(){
        ExecutorService threadPool2 = Executors.newFixedThreadPool(5); //创建一个内含线程数的大小固定的线程池

        try {
            for (int i = 1; i <= 10; i++) {
                /*
                 * 传统新建线程是new Thread.start()，但是现在是用线程池创建线程
                 * */
                //通过线程池创建线程，在execute方法中丢一个Runnable(具体的线程)进去
                threadPool2.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池用完，程序结束，必须要关闭线程池
            threadPool2.shutdown();
        }
    }

    //使用“可伸缩的线程池”
    public static void test3(){
        ExecutorService threadPool3 = Executors.newCachedThreadPool(); //可伸缩的线程池（遇强测强 遇弱则弱）

        try {
            for (int i = 1; i <= 10; i++) {
                /*
                 * 传统新建线程是new Thread.start()，但是现在是用线程池创建线程
                 * */
                //通过线程池创建线程，在execute方法中丢一个Runnable(具体的线程)进去
                threadPool3.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池用完，程序结束，必须要关闭线程池
            threadPool3.shutdown();
        }
    }
}

