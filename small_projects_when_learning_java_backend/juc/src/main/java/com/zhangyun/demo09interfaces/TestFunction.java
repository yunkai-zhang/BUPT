package com.zhangyun.demo09interfaces;

import java.util.function.Function;

public class TestFunction {
    public static void main(String[] args) {
        Function<String, String> function = (str) -> {return str;};
        System.out.println(function.apply("20220317"));
    }
}
