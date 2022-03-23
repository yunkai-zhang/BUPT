package com.zhangyun.demo02oom;

import java.util.ArrayList;

public class TestJprofiler {
    //每一个testjprofile对象都被分配了1M的堆内存，所以不停创建对象并存储的话，会oom
    byte [] byteArray= new byte[1024*1024];//1M

    public static void main(String[] args) {
        //指定list列表存储的类型为本类TestJprofiler实例化的对象
        ArrayList<TestJprofiler> list = new ArrayList<>();

        //计数
        int count= 0;

        //利用死循环构建oom;可能会出现异常的代码块，要总是用trycatch
        try {
            while(true){
                list.add(new TestJprofiler());
                count++;
            }
        }
        //Exception是捕获不到的，要用error
        catch (Error e) {
            /*
            * 顶级不正常是Throwable
            * 次一层级是Exception和Error
            * */
            System.out.println("count:"+count);
            e.printStackTrace();
        }

    }
}
