package com.zhangyk.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class Test11 {

    public void test01(Map<String,User> map, List<User> list){
        System.out.println("test01");
    }
    public Map<String,User> test02(){
        System.out.println("test02");
        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException {

        System.out.println("=======================针对test01方法，尝试打印泛型参数========================");

        //利用反射获取方法对象
        Method method = Test11.class.getMethod("test01", Map.class, List.class);
        //获得方法中的泛型参数类型
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        //针对每个泛型参数进行处理
        for (Type genericParameterType : genericParameterTypes) {
            System.out.println("#"+genericParameterType);
            //如果某泛型属于参数化类型，把泛型里包含的每一具体类型打印出来
            if(genericParameterType instanceof ParameterizedType){
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println(actualTypeArgument);
                }
            }
        }

        System.out.println("=======================针对test02方法，尝试打印泛型返回值========================");
        //利用反射获取方法对象
        method = Test11.class.getMethod("test02",null);
        //返回值只可能有一个，所以方法名结尾不带s
        Type genericReturnType = method.getGenericReturnType();
        //如果泛型返回值类型属于参数化类型，把泛型里包含的每一具体类型打印出来
        if(genericReturnType instanceof ParameterizedType){
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println(actualTypeArgument);
            }
        }

    }
}
