package com.zhangyun.demo09interfaces;

import java.util.function.Predicate;

public class TestPrediction {
    public static void main(String[] args) {
        //return str.isEmpty();没有做空指针预防，应该改写为如下方式
        Predicate<String> predicate = (str) -> {return "".equals(str);};
        // false
        System.out.println(predicate.test("aaa"));
        // true
        System.out.println(predicate.test(""));
    }
}
