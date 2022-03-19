package com.zhangyun.syn;

import java.util.ArrayList;
import java.util.List;

public class UnsafeList {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            }).start();
        }

        //网友：休眠的意义在于将所有创建的线程都添加进数组,防止cpu调用到主线程,然后输出数组长度
        Thread.sleep(3000);

        System.out.println(list.size());
    }
}
