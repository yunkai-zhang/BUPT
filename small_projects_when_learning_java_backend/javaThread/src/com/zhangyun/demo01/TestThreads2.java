package com.zhangyun.demo01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TestThreads2 extends Thread{
    private String url;//网络图片地址
    private String name;//报错扥文件名

    //有参构造
    public TestThreads2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    //下载图片线程的执行体
    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载了文件名为:" + name);
    }

    public static void main(String[] args) {
        TestThreads2 t = new TestThreads2("https://img-home.csdnimg.cn/images/20201124032511.png", "1.png");
        TestThreads2 t1 = new TestThreads2("https://img-home.csdnimg.cn/images/20201124032511.png", "2.png");
        TestThreads2 t2 = new TestThreads2("https://img-home.csdnimg.cn/images/20201124032511.png", "3.png");
        t.start();
        t1.start();
        t2.start();
    }
}

//下载器
class WebDownloader {
    //下载方法
    public void downloader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常,downloader方法出现问题");
        }
    }
}
