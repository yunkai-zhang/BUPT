package com.zhangyk.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

//获得类的信息
public class Test08 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {

        //获得类的名字
        System.out.println("=================获得类的名字=================");
        //通过Class.forName获取类对象
        Class c1 = Class.forName("com.zhangyk.reflection.User");
        //获得类的名字
        System.out.println(c1.getName()); //获得包名+类名
        System.out.println(c1.getSimpleName());//获得类名

        //通过object.getClass获取类对象
        User user = new User();
        Class c2 = user.getClass();
        //获得类的名字
        System.out.println(c1.getName()); //获得包名+类名
        System.out.println(c1.getSimpleName());//获得类名

        //获得类的属性
        System.out.println("==================获得类的属性================");
        //getFields()只能找到public属性；打印时发现因为属性都是私有的，没有东西被打印出来
        Field[] fields = c1.getFields();
//        for (Field field : fields) {
//            System.out.println(field);
//        }
        //getDeclaredFields()可以找到包含public和private的所有属性
        Field[] declaredFields = c1.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }

        //获得指定属性的值
        System.out.println("==================获得指定属性的值================");
        Field name = c1.getDeclaredField("name");
        System.out.println(name);

        //获得类的方法
        System.out.println("==================获得类的方法================");
        //getMethods()：获得本类及其父类的全部public方法
        Method[] methods = c1.getMethods();
        for (Method method : methods) {
            System.out.println("getMethods()得到的"+ method);
        }
        //getDeclaredMethods()：获得本类的所有方法
        methods = c1.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("getDeclaredMethods()得到的"+ method);
        }

        //获取指定的方法
        System.out.println("==================获取指定的方法================");
        /*之所以getMethod需要参数，是因为考虑方法可能有重载

        重载定义：重载(overloading) 是在一个类里面，方法名字相同，而参数不同。返回类型可以相同也可以不同。参数类型的
        顺序不同也算重载
        */
        Method getName = c1.getMethod("getName", null);
        Method setName = c1.getMethod("setName", String.class);
        System.out.println(getName);
        System.out.println(setName);

        //获得类的构造器
        System.out.println("==================获得类的构造器================");
        //getConstructors()获得public的构造器
        Constructor[] constructors = c1.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
        //getDeclaredConstructors()获得全部的构造器
        Constructor[] declaredConstructors = c1.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }

        //获得指定的构造器
        System.out.println("==================获得指定的构造器================");
        Constructor declaredConstructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        System.out.println(declaredConstructor);


    }
}
