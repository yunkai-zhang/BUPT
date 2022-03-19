package com.zhangyun.proxystatic;

public class StaticProxy1 {
    public static void main(String[] args) {
        new Thread(()-> System.out.println("我爱你")).start();
        new WeddingCompany(new You()).happyMarry();
    }
}
//WeddingCompany...上一个文件定义过了这里就直接使用了