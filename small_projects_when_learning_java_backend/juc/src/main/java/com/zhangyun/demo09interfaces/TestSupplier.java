package com.zhangyun.demo09interfaces;

import java.util.function.Supplier;

public class TestSupplier {
    public static void main(String[] args) {
        Supplier<String> supplier=new Supplier<String>() {
            @Override
            public String get() {
                return "where should I live？";
            }
        };

        System.out.println(supplier.get());
    }
}
