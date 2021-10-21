package com.zhangyk.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//分析性能问题
public class Test10 {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        test01();
        test02();
        test03();
    }

    //普通方法调用
    public static void test01(){
        User user = new User();

        long startTime = System.currentTimeMillis();

        for(int i=0;i<1000000000;i++){
            user.getName();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("普通方法执行10亿次："+(endTime-startTime)+"毫秒");
    }

    //反射方法调用
    public static void test02() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        Class c1 = user.getClass();

        Method getName = c1.getDeclaredMethod("getName", null);

        long startTime = System.currentTimeMillis();

        for(int i=0;i<1000000000;i++){
            getName.invoke(user, null);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("反射方法执行10亿次："+(endTime-startTime)+"毫秒");
    }

    //反射方法调用+关闭检查
    public static void test03() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        Class c1 = user.getClass();

        Method getName = c1.getDeclaredMethod("getName", null);
        //针对setName方法对象关闭检查，即为setName方法对象赋权允许访问
        getName.setAccessible(true);

        long startTime = System.currentTimeMillis();

        for(int i=0;i<1000000000;i++){
            getName.invoke(user, null);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("反射方法+关闭检查执行10亿次："+(endTime-startTime)+"毫秒");
    }
}
