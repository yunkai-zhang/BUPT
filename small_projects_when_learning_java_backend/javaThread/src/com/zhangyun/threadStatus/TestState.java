package com.zhangyun.threadStatus;

public class TestState {
    public static void main(String[] args) throws InterruptedException {

        //以lamda表达式的方法编写run方法，表示线程启动后会休眠5秒
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("//");
        });

        //观察线程启动前的状态
        Thread.State state = thread.getState();
        System.out.println(state);

        //观察线程启动后的状态
        thread.start();
        state = thread.getState();
        System.out.println(state);//Run

        //自定义线程睡眠5秒后会会执行完所有操作，便死亡
        while (state != Thread.State.TERMINATED) {//只要现成不终止,就一直输出状态
            Thread.sleep(100);
            state = thread.getState();//更新线程状态
            System.out.println(state);
        }
        //死亡后的线程不能再启动了,启动会报异常
        //thread.start();
    }
}
