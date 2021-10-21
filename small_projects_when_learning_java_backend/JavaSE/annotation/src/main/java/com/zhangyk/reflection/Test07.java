package com.zhangyk.reflection;

public class Test07 {

    public static void main(String[] args) throws ClassNotFoundException {
        //获取系统类的加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        //获取系统类加载器的父类加载器：扩展类加载器
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);

        //获取扩展类加载器的父类加载器：根加载器（c/c++）
        //这个无法直接获得，所以测试结果必定为null
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);

        //测试当前类是哪个加载器加载的：用户类加载器
        ClassLoader classLoader = Class.forName("com.zhangyk.reflection.Test07").getClassLoader();
        System.out.println(classLoader);

        //测试jdk内置的类是哪个加载器加载的：根加载器
        ClassLoader classLoader1 = Class.forName("java.lang.Object").getClassLoader();
        System.out.println(classLoader1);

        //如何获得系统类加载器可以加载的路径
        System.out.println(System.getProperty("java.class.path"));
        /*
        * 输出如下：
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\charsets.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\deploy.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\ext\access-bridge-64.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\ext\cldrdata.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\ext\dnsns.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\ext\jaccess.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\ext\jfxrt.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\ext\localedata.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\ext\nashorn.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\ext\sunec.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\ext\sunjce_provider.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\ext\sunmscapi.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\ext\sunpkcs11.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\ext\zipfs.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\javaws.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\jce.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\jfr.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\jfxswt.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\jsse.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\management-agent.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\plugin.jar;D:\eclipse\java\jdk1.8.0_121\jre\lib\resources.jar;
        * D:\eclipse\java\jdk1.8.0_121\jre\lib\rt.jar;
        * D:\idea\JavaSE\annotation\target\classes;
        * C:\Program Files\JetBrains\IntelliJ IDEA 2018.3.6\lib\idea_rt.jar

        科普双亲委派机制：系统会按顺序：用户类加载器-》扩展类加载器-》根加载器 搜索类有没有被定义，如果自己定义的类和
        加载器加载的类有重名，自己定义的类直接无效。比如自己定义java.lang.String的类是永远跑不了的，因为该类在根加载器中
        被定义
         * */
    }
}
