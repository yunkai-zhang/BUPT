package com.zhangyk.reflection;

import java.lang.annotation.ElementType;

//所有类型的class
public class Test04 {
    public static void main(String[] args) {
        //class
        Class c1 = Object.class;

        //interface
        Class c2 = Comparable.class;

        //[]数组:一维，二维
        Class c3 = String[].class;
        Class c4 = int[][].class;

        //annotation
        Class c5 = Override.class;

        //enum
        Class c6 = ElementType.class;

        //primitives
        Class c7 = Integer.class;
        
        //void
        Class c8 = void.class;

        //Class本身也是一个类
        Class c9 = Class.class;

        //技巧：alt+c 可以竖着复制
        //打印显示所有类型的Class对象
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
        System.out.println(c8);
        System.out.println(c9);

        //只要元素类型和维度一样，就对应同一个Class对象
        int[]a=new int[10];
        int[]b=new int[100];
        System.out.println(a.getClass().hashCode());
        System.out.println(b.getClass().hashCode());
    }
}
