package com.zhangyun.demo13jmmvolatile;

import java.util.concurrent.TimeUnit;

public class Volatile01 {
    // 如果不加volatile 线程1会死循环，导致本volatile01程序无法结束
    // 加了volatile是可以保证可见性的，即子线程1就能看见主内存的变化
    //static是为了保证在main函数中能用
    private volatile static int number = 0;

    public static void main(String[] args) {

        //子线程1
        new Thread(()->{
            //只要number还是0，就会一直循环，子线程就不会停止，本程序也就不会停止
            while (number==0){
            }
        }).start();

        //让main线程sleep1s，保证子线程1一定可以在“main线程把number改成1”前先起动；因为1s够CPU切换多次时间片，99.9999%的概率能轮到子线程1；
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        number=1;
        System.out.println(number);
    }
}
