package com.zhangyun.demo04;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //借助FutureTask构建Callable和Thread之间的关系
        //new Thread(new Runnable()).start();
        //new Thread(new FutureTask<V>()).start();
        //new Thread(new FutureTask<V>( Callable)).start();

        //创建Callable的实现类的实例化对象thread
        MyThread thread= new MyThread();
        //创建适配类FutureTask的实例化对象futuretask
        FutureTask<String> futureTask = new FutureTask<String>(thread);
        //放入Thread使用。两个线程启动，但是call方法只被执行一次，说明结果会被缓存，效率高(缓存学了CAS后可能会更懂)。
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();

        //获取返回值;get方法产生阻塞，我们把它放到最后一行(或者使用异步通信处理)
        String s = futureTask.get();

        System.out.println("返回值："+ s);
    }
}

class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("Call:"+Thread.currentThread().getName());
        //耗时的操作
        return "String"+Thread.currentThread().getName();
    }
}
