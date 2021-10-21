package com.zhangyk.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test09 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        //获得Class对象
        Class c1 = Class.forName("com.zhangyk.reflection.User");

        //通过反射调用普通方法
        User user = (User) c1.newInstance();
        Field name = c1.getDeclaredField("name");

        //不能直接操作私有属性or私有方法，我们要为需要访问的私有属性or私有方法打开权限：setAccessible(true)
        name.setAccessible(true);
        name.set(user,"张云2");
        System.out.println(user.getName());
    }


}
