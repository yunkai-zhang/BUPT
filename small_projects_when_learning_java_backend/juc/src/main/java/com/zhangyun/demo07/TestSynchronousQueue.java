package com.zhangyun.demo07;

//import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * Description：
 *
 * @author jiaoqianjin
 * Date: 2020/8/12 10:02
 **/

public class TestSynchronousQueue {
    public static void main(String[] args) {
        //创建一个同步队列的实例化对象；队列规范，最好在前部指定好泛型，后面可以不写泛型，因为泛型类型可以推断
        BlockingQueue<String> synchronousQueue = new java.util.concurrent.SynchronousQueue<>();

        // 网queue中添加元素
        new Thread(() -> {
            try {
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName() + "：put 1");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName() + "：put 2");
                synchronousQueue.put("3");
                System.out.println(Thread.currentThread().getName() + "：put 3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 取出元素
        new Thread(()-> {
            try {
                System.out.println(Thread.currentThread().getName() + "：take " + synchronousQueue.take());
                System.out.println(Thread.currentThread().getName() + "：take " + synchronousQueue.take());
                System.out.println(Thread.currentThread().getName() + "：take " + synchronousQueue.take());
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}


