package com.zhangyun.demo15locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLockReentrant {

    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        new Thread(()->{
            phone.sms();
        },"A").start();
        new Thread(()->{
            phone.sms();
        },"B").start();
    }

}
class Phone2{

    Lock lock=new ReentrantLock();

    public void sms(){
        //lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"=> sms");
            call();//这个call方法也会给对象再加一次锁，且加的是同一把锁;这就是可重入锁！
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //lock锁必须配对，即有一个lock就得有一个对应的unlock，否则会因为少解了把锁，导致线程无法释放对象锁
            //lock.unlock();
            lock.unlock();
        }
    }

    public void call(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "=> call");
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

}
