package com.zhangyun.demo13single;

//饿汉式单例
public class Hungry {
    /**
     * 这四组数据很耗内存资源，这个饿汉式一上来就把类中包含四个数组的所有的东西都加载进内存，但是这个数组又很可能是一开始没被使用的，
     * 就浪费了空间
     */
    private byte[] data1=new byte[1024*1024];
    private byte[] data2=new byte[1024*1024];
    private byte[] data3=new byte[1024*1024];
    private byte[] data4=new byte[1024*1024];



    private Hungry(){

    }
    private final static Hungry hungry = new Hungry();

    public static Hungry getInstance(){
        return hungry;
    }


}
