package com.zhangyk.demo03;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//等会会用这个类自动生成代理类，而不是像demo1中写死代理类
public class ProxyInvocationHandler implements InvocationHandler {
    /*
    * 参考官方文档使用newProxyInstance():
    * Foo f = (Foo) Proxy.newProxyInstance(
    * //第一个参数：ClassLoader()；代理类入口
    * Foo.class.getClassLoader(),
    * //第二个参数：接口数组；被代理的类（Host）的接口
    * new Class<?>[]{Foo.class},
    * //第三个参数：InvocationHandler；如何代理实现的方法的位置
    * handler);
    * */

    //实现了被代理的接口的某类的对象。这里写死为只能代理Rent接口
    private Rent rent;
    //对象通过public set设置
    public void setRent(Rent rent){
        this.rent=rent;
    }

    //生成得到代理对象
    public Object getProxy(){
        /*
        * 下面这行代码是死的，只要改第二个参数的rent就可以了
        * */
        return Proxy.newProxyInstance(
                //代理类入口
                this.getClass().getClassLoader(),
                //被代理的类（Host）的接口
                rent.getClass().getInterfaces(),
                //如何代理实现的方法的位置
                this);
    }

    //处理代理实例proxy调用的方法，并返回结果，此结果没有明显用处
    /*
    * invoke是jvm调的
    *
    * 代理实例proxy调用了任何方法，都会通过该强制实现的invoke方法来反射实现那些方法
    * */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        seeHouse();

        /*
        * 用反射来执行方法,之前反射讲过，经典
        *
        * 动态代理的本质，就是使用反射机制实现
        *
        * 这里的method对象是proxy对象调用的方法的方法对象；本例子中是rent方法（可见于Client类的第20行）
        *
        * 这里的rent是实现了Rent接口的某类的一个对象；本例子中rent为host对象（Client类中用setRent赋值的）。
        * 由于Client类中，代理proxy调用了rent方法，这里method便是rent方法的方法类，
        * 此rent方法的方法类“method”，通过invoke，作用于实现了Rent接口的对象rent，而该rent对象是host对象，
        * 所以此行invoke本质是执行host对象中的rent方法。
        * */
        Object result = method.invoke(rent, args);

        fare();

        return result;
    }

    public void seeHouse(){
        System.out.println("中介带看房子");
    }

    public void fare(){
        System.out.println("中介收钱");
    }
}
