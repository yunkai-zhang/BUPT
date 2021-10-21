package com.zhangyk.reflection;

//测试类什么时候会初始化
public class Test06 {

    static {
        System.out.println("main类被加载");
    }

    public static void main(String[] args) throws ClassNotFoundException {

        //不会产生类的引用的方法

        //调用常量
        System.out.println(Son.M);

    }
}

class Father{

    static int b=2;
    static{
        System.out.println("父类被加载");
    }
}

class Son extends Father{
    static {
        System.out.println("子类被加载");
    }

    static int m = 100;
    //常量一般用大写，在常量池里
    static final int M=1;
}
