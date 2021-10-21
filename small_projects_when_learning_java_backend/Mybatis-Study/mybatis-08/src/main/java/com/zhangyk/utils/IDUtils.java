package com.zhangyk.utils;

import org.junit.Test;

import java.util.UUID;

//如果一直报一些没必要的warning，可以用下面的注解。它把所标注类的所有警告都去除
//@SuppressWarnings("all")
public class IDUtils {

    //公司中，像id这样的项很少用1234567....，因为删除第5项后，id为5的项就空缺了；工作中常使用随机的uuid当做id；
    public static String getId(){
//      第一项regex表示正则，如果使用点号的话要转义，因为点号在正则中相当于匹配所有字符
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    @Test
    public void test(){
        System.out.println(IDUtils.getId());
    }

}
