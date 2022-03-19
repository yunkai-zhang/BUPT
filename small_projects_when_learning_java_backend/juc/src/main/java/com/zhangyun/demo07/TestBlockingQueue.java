package com.zhangyun.demo07;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestBlockingQueue {
    public static void main(String[] args) {
        //test1();
        //test2();
//        try {
//            test3();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            test4();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 处理队列中不合理的指令的方式1：抛出异常
     */
    public static void test1(){
        //ArrayBlockingQueue的构造函数需要填入初始化队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        //往队列添加元素
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //如果队列满了还要继续往队列添加元素，会抛出异常：java.lang.IllegalStateException: Queue full
        //System.out.println(blockingQueue.add("d"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //如果队列空了再多移除一个，这也会抛出异常： java.util.NoSuchElementException
        //System.out.println(blockingQueue.remove());
    }
//=======================================================================================
    /**
     * 处理队列中不合理的指令的方式2：不抛出异常，有返回值
     */
    public static void test2(){
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        //添加 一个不能添加的元素 使用offer只会返回false 不会抛出异常
        System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //弹出 如果没有元素 只会返回null 不会抛出异常
        System.out.println(blockingQueue.poll());
    }
//=======================================================================================
    /**
     * 处理队列中不合理的指令的方式3：等待 一直阻塞
     */
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        //一直阻塞 不会返回
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");

        //如果队列已经满了， 再进去一个元素  这种情况会一直等待这个队列 什么时候有了位置再进去，程序不会停止
        //blockingQueue.put("d");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        //如果我们再来一个  这种情况也会等待，程序会一直运行 阻塞
        System.out.println(blockingQueue.take());
    }
//=======================================================================================
    /**
     * 处理队列中不合理的指令的方式4：等待 超时阻塞
     *
     *  这种情况也会等待队列有位置 或者有产品 但是会超时结束
     */
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        System.out.println("开始等待");
        //在队列是满的情况下，要往里面加d，就得等待；因为超时时间是2s 所以如果等待超过2s队列还是没有位置腾出来，就结束等待
        blockingQueue.offer("d",2, TimeUnit.SECONDS);
        System.out.println("结束等待");
        System.out.println("===========取值==================");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println("开始等待");
        blockingQueue.poll(2, TimeUnit.SECONDS); //超过两秒 我们就不要等待了
        System.out.println("结束等待");
    }


}
