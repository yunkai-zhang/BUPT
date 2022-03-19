package com.zhangyun.demo06;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyCacheLock myCache = new MyCacheLock();
        int num = 5;

        //写入
        for (int i = 1; i <= num; i++) {
            int finalI = i;
            new Thread(() -> {

                myCache.write(String.valueOf(finalI), String.valueOf(finalI));

            },String.valueOf(i)).start();
        }

        //读取
        for (int i = 1; i <= num; i++) {
            int finalI = i;
            new Thread(() -> {

                myCache.read(String.valueOf(finalI));

            },String.valueOf(i)).start();
        }
    }
}

/**
 *  自定义缓存，用MyCahceLock类实现
 *
 *  方法已加锁，导致写的时候被插队
 */
class MyCacheLock {
    //写一个map来维护缓存；这个缓存是伪造的，随便写的，理解一下这个意思即可；后面会专门讲缓存
    private volatile Map<String, String> map = new HashMap<>();


    /*需要锁的时候先new一个锁出来。ReentrantLock是一个普通的锁，我们这里使用更加细粒度的ReadWriteLock锁。
    *
    * 读写锁的好处：更加细粒度的控制。
    * 所谓更细粒度，就是写入的时候只希望缓存区被一个线程写，读的时候缓存区可以被所有线程读
    * */
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();

    //存
    public void write(String key, String value) {
        //往缓存存东西的时候，加写锁
        readWriteLock.writeLock().lock();

        //用trycatchfinally（idea有快捷键）保证锁一定会被释放。加锁和解锁之间的代码块，即为同步的代码块，即被锁住的代码块
        try {
            System.out.println(Thread.currentThread().getName() + "线程开始写入");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "线程写入ok");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    //取
    public void read(String key) {
        //写和读是互斥的，即有线程在读的时候不允许线程写，有线程写的时候不允许线程读；所以虽然允许多个线程同时读缓存区，但是因为写锁的存在，必须给读操作加读锁
        readWriteLock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "线程开始读取");
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "线程读取ok");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

