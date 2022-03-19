package com.zhangyun.demo15locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class TestSpinLock {
    public static void main(String[] args) throws InterruptedException {

        //使用CAS实现自定义自旋锁
        SpinlockDemo spinlockDemo=new SpinlockDemo();

        //线程1启动
        new Thread(()->{
            //加锁，休息3s，再解锁
            spinlockDemo.myLock();
            try {
                /*
                * 线程t1睡1003ms，则线程t2不能打印出“自旋中”，线程t1睡1004ms，则线程t2能打印出“自旋中”；
                * 这个4ms表示，线程t1解锁需要的时间；
                * 如果线程t1多休眠一会再解锁，则主线程休眠1s后启动线程t2，t2就不能直接拿到自旋锁，t2得进入自旋等待状态等待线程t1休眠完毕解锁才能拿到spinlockDemo的自旋锁
                * */
                TimeUnit.MILLISECONDS.sleep(1109);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinlockDemo.myUnlock();
            }
        },"t1").start();

        //保证t1线程先获取到spinlockDemo的自旋锁
        TimeUnit.SECONDS.sleep(1);

        //线程2启动
        new Thread(()->{
            spinlockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinlockDemo.myUnlock();
            }
        },"t2").start();
    }
}
class SpinlockDemo {

    /*
    * 通过原子引用(AtomicReference)来实现CAS，进而实现自旋锁。
    * 我想锁一个线程，就判断线程的原子引用，即在<>中填入Thread
    *
    * 这里new AtomicReference的构造函数中没有填入内容，表示此时atomicReference引用的Thread类型为null没有值。关于默认值，回顾javase：
    * Integer默认值是 0
    * Thread是一个类，叫引用类型，默认值是null
    * */
    AtomicReference<Thread> atomicReference=new AtomicReference<>();

    //加锁
    public void myLock(){
        //获取当前线程的线程对象
        Thread thread = Thread.currentThread();

        /*加锁的时候，就不断判断这个东西有没有加锁就完事了，这个自旋锁
        *
        * 没抢到SpinlockDemo实例化对象的自旋锁，就让他一直卡在这；
        * 用CAS比较，如果atomicReference引用的Thread类型为空，就把atomicReference引用的Thread类型的值设置本线程对象，
        * 然后compareAndSet返回true离开while循环，相当于抢到了SpinlockDemo实例化对象的自旋锁。
        * 如果atomicReference引用的Thread类型不为空，即值被其他线程设置为别的线程了，执行compareAndSet就会返回false，
        * 导致一直自旋等待拿到SpinlockDemo实例化对象的自旋锁的线程释放锁。
        * */
        while (!atomicReference.compareAndSet(null,thread)){
            System.out.println(Thread.currentThread().getName()+" ==> 自旋中~");
        }

        //打印，表示本线程抢到SpinlockDemo实例化对象的自旋锁
        System.out.println(thread.getName()+"===> mylock");
    }


    //解锁
    public void myUnlock(){
        //获取当前线程的线程对象
        Thread thread=Thread.currentThread();

        //打印，表示本线程释放之前抢到的SpinlockDemo实例化对象的自旋锁；本打印一定要在真实解锁前做，否则先解锁后打印，可能会出现线程t1还没打印释放锁，线程t2就打印已加锁的现象
        System.out.println(thread.getName()+"===> myUnlock");

        /*
        * 解锁：如果atomicReference引用的线程是本线程，即是本线程拿到了SpinlockDemo实例化对象的自旋锁，
        * 就把atomicReference引用的线程设为空；即释放掉本线程对于SpinlockDemo实例化对象的自旋锁
        * */
        atomicReference.compareAndSet(thread,null);
    }

}
