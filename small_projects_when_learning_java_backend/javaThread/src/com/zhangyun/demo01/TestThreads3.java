package com.zhangyun.demo01;

public class TestThreads3 implements Runnable {

    @Override
    public void run() {
        //run方法线程体
        for (int i = 0; i < 20; i++) {
            System.out.println("线程1----" + i);
        }
    }

    public static void main(String[] args) {
        //创建runnable接口的实现类对象
        TestThreads3 testThread = new TestThreads3();
        //创建线程对象时,把Runnable实现类的对象丢进来，通过线程对象来开启我们的线程。代理
        Thread thread = new Thread(testThread);
        //调用start（）开启线程
        thread.start();

        //new Thread(testThread).start();
        for (int i = 0; i < 200; i++) {
            System.out.println("线程2----" + i);
        }
    }

}
