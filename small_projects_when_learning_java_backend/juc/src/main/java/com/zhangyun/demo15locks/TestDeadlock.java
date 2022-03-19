package com.zhangyun.demo15locks;

import java.util.concurrent.TimeUnit;

public class TestDeadlock {
    public static void main(String[] args) {
        //基本类型中String的传递方式是特殊的，往方法中传递的是引用，即可理解为传递了一个对象进去；所以下面两个String对象，可以作为线程类中同步块的锁
        String lockA= "lockA";
        String lockB= "lockB";

        /*
        * 线程t1占着对象lockA的锁，想拿对象lockB的锁；
        *
        * 之所以占着对象lockA的锁，是因为构造函数最左边的参数会作为MyThread类中的lockGotten，即嵌套同步块的外层同步块的锁;
        * 只有先拿到外部同步块的锁，才能进入外部同步块并参与竞争内部同步块的锁
        * */
        new Thread(new MyThread(lockA,lockB),"t1").start();
        //线程t2占着对象lockB的锁，想拿对象lockA的锁
        new Thread(new MyThread(lockB,lockA),"t2").start();
    }
}

//写一个线程类，在线程类中放两个资源；开两个线程，让线程互相争抢
class MyThread implements Runnable{

    private String lockGotten;
    private String lockToGet;

    public MyThread(String lockGotten, String lockToGet) {
        this.lockGotten = lockGotten;
        this.lockToGet = lockToGet;
    }

    @Override
    public void run() {
        //拿到对象lockGotten的锁，才可进入下面同步块
        synchronized (lockGotten){
            System.out.println(Thread.currentThread().getName()+" have gotten "+lockGotten+"===>want to get "+lockToGet);

            //本线程睡2s，是为了让另一个线程无竞争地拿到lockToGet对象的锁
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //线程能运行到本位置，说明已经拿到了lockGotten的锁；想进入下面这个同步块，就得拿到lockToGet的锁，但是lockToGet的锁已经被另一个线程拿到了
            synchronized (lockToGet){
                System.out.println(Thread.currentThread().getName()+" have gotten "+lockToGet+"===>want to get "+lockGotten);
            }
        }
    }

}
