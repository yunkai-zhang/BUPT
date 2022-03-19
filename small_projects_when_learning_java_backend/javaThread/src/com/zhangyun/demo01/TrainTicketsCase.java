package com.zhangyun.demo01;
/**
 * 多个线程同时操作同一个对象  买火车票案例
 */
//发现问题:多个线程操作同一个资源的情况下,线程不安全,数据紊乱
public class TrainTicketsCase implements Runnable{
    //票数
    private int ticketNums = 10;

    //run表示本线程不停地抢票，只要本线程被分配了cpu资源就抢。
    @Override
    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            //捕获异常
            try {
                //真实买票的时候会有延时，线程中的sleep可以模拟延时；避免某个线程在自己分配到cpu时就把10张票抢光（票数多的话，自己的片抢不光，就还能剩下点票给别的线程）。
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //getname获取当前线程的名字。
            System.out.println(Thread.currentThread().getName() + "--->拿到了第" + ticketNums-- + "张票");
        }
    }

    public static void main(String[] args) {
        TrainTicketsCase ticket = new TrainTicketsCase();
        //开启三个线程，操作系统轮流给三个线程分配cpu资源，让它们内卷抢票。
        new Thread(ticket, "小红").start();
        new Thread(ticket, "老师").start();
        new Thread(ticket, "黄牛").start();
    }
}
