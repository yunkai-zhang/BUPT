package com.zhangyun.threadStatus;
/**
 * 线程优先级
 */
public class ThreadPriority {

    public static void main(String[] args) {
        //查看主线程的默认优先级
        System.out.println(Thread.currentThread().getName()+"的优先级为"+Thread.currentThread().getPriority());

        //自定义线程
        MyPriority myPriority = new MyPriority();
        Thread thread1 = new Thread(myPriority);
        Thread thread2 = new Thread(myPriority);
        Thread thread3 = new Thread(myPriority);
        Thread thread4 = new Thread(myPriority);
        Thread thread5 = new Thread(myPriority);

        //先设置优先级,再启动
        thread1.setPriority(1);
        thread2.setPriority(2);
        thread3.setPriority(3);
        thread5.setPriority(Thread.MAX_PRIORITY);//MAX_PRIORITY=10

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}

class MyPriority implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"的优先级为"+Thread.currentThread().getPriority());
    }
}


