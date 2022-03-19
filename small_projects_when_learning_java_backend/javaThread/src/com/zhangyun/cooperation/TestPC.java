package com.zhangyun.cooperation;

// 生产者,消费者,产品,缓冲区
public class TestPC {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();

        //由于Productor和Consumer是继承Thread类，而非实现Runnable接口，故不需要在Productor和Consumer构造时传入Runnable的实现类的对象
        //线程开启后，会运行承载线程的对象的run函数中的内容；对于本例，就是生产者和消费者不停调用缓冲区类的pop和push函数。
        new Productor(container).start();
        new Consumer(container).start();
    }

}

//用消费和生产鸡的缓冲区
class SynContainer {
    //需要一个盘子的大小
    Chicken[] chickens = new Chicken[10];
    //盘子装鸡计数器
    int count = 0;


    //在缓冲区中，把生产者生产的鸡放入盘子
    //?问答问:这里push方法加synchronized有用吗？
    public synchronized void push(Chicken chicken) {
        //如果盘子满了,就需要等待消费者消费；盘子不满时才能把传入的鸡装入盘子
        if (count == chickens.length) {
            //通知消费者消费,生产等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //wait过后，本线程被唤醒，说明盘子中的鸡有被消费了，盘子现在不为满了。
        }
        //本线程能执行到这个位置，说明现在盘子不满，可以放入生产者传入的鸡
        chickens[count] = chicken;
        count++;
        //必须在notify前打印，否则notify会通知别的线程结束wait状态并消费，导致打印的鸡不准
        System.out.println("生产了第" + chicken.id + "只鸡");
        //?问答问：本盘子对象没有开启线程，是生产者和消费者开启了线程并把本盘子对象传入生产者消费者对象，那是意味着本盘子进入线程了吗
        //可以通知消费了
        this.notifyAll();
    }


    //消费者消费产品
    public synchronized void pop() {
        //根据盘子中鸡的数目，判断能否消费
        if (count == 0) {
            //等待生产者生产,消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //wait过后，本线程被唤醒，说明盘子中的鸡有被消费了，盘子现在不为满了
        }
        //缓冲区的消费方法能走到这，说明盘子中鸡的数目不为空
        count--;
        Chicken chicken = chickens[count];
        //必须在notify前打印，否则notify会通知别的线程结束wait状态并生产，导致打印的鸡不准
        System.out.println("消费了-->第" + chicken.id + "只被生产的鸡");
        //可以通知生产了
        this.notifyAll();
    }
}

// 生产者
class Productor extends Thread {
    SynContainer container;

    public Productor(SynContainer container) {
        //生产者和消费者共享一个container
        this.container = container;
    }

    //生产者不停生产100只鸡，并转给缓冲区
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            //i+1是为了让鸡的id从1开始
            container.push(new Chicken(i+1));
        }
    }
}

//消费者
class Consumer extends Thread {
    SynContainer container;

    public Consumer(SynContainer container) {
        //生产者和消费者共享一个container
        this.container = container;
    }

    //生产者不停地从缓冲区拿鸡，并消费100只鸡
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            container.pop();
        }
    }
}


// 产品
class Chicken {
    int id;  //产品编号

    public Chicken(int id) {
        this.id = id;
    }
}


