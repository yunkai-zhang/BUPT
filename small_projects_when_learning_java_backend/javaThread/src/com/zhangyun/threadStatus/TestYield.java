package com.zhangyun.threadStatus;
/**
 * 测试礼让线程
 * 礼让不一定成功,看cpu心情
 */
public class TestYield {

        public static void main(String[] args) {
            MyYeild myYeild = new MyYeild();
            new Thread(myYeild, "a").start();
            new Thread(myYeild, "b").start();
        }
}

    class MyYeild implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "线程开始执行");
            Thread.yield();//礼让
            System.out.println(Thread.currentThread().getName() + "线程停止执行");
        }


}
