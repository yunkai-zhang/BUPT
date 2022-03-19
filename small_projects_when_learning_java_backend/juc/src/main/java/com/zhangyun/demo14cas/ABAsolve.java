package com.zhangyun.demo14cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABAsolve {
    /**AtomicStampedReference 注意，如果泛型是一个基本类型的包装类，注意对象的引用问题
     * 正常在业务操作，这里面比较的都是一个个对象
     *
     * 构造函数输入（要保证原子性的对象，初始版本戳）；每次修改引用的对象，都应改变版本戳
     */
    static AtomicStampedReference<String> atomicStampedReference = new
            AtomicStampedReference<>("2020", 1);

    // CAS compareAndSet : 比较并交换！
    public static void main(String[] args) {
        //a线程
        new Thread(() -> {
            // a线程每次启动后，先获得原子引用的版本号
            int stamp = atomicStampedReference.getStamp();
            System.out.println("a1Stamp=>" + stamp);

            //让ab线程都休眠1s，保证线程刚启动后拿到的是原子引用的同一个版本号
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 修改操作时，版本号更新 + 1;打印CAS的结果
            System.out.println("a2CAS=>"+atomicStampedReference.compareAndSet("2020", "2021",
                    atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1));
            //修改完毕后，打印修改完毕后的原子引用的版本号
            System.out.println("a2Stamp=>" + atomicStampedReference.getStamp());
            System.out.println("a2Refer=>" + atomicStampedReference.getReference());

            // 本线程重新把原子引用的值改回去， 版本号更新 + 1;打印CAS的结果
            System.out.println("a3CAS=>"+atomicStampedReference.compareAndSet("2021", "2020",
                    atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1));
            //修改完毕后，打印修改完毕后的原子引用的版本号
            System.out.println("a3Stamp=>" + atomicStampedReference.getStamp());
        }, "a").start();

        // b线程；操作与乐观锁的原理相同，即a线程做完的操作，b线程得是知道的
        new Thread(() -> {
            // b线程每次启动后，先获得原子引用的版本号
            int stamp = atomicStampedReference.getStamp();
            System.out.println("b1Stamp=>" + stamp);

            //让ab线程都休眠1s，保证线程刚启动后拿到的是原子引用的同一个版本号
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //b线程再额外休眠4s，保证能等A线程先把值改为2021再改回来
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("b2CAS=>"+atomicStampedReference.compareAndSet("2020", "2222",
                    stamp, stamp + 1));
            System.out.println("b2Stamp=>" + atomicStampedReference.getStamp());
        }, "b").start();
    }
}


