package com.zhangyun.enum1;

public class TestEnum {

    // 输出
    public static void main(String[] args)
    {
        Color c1 = Color.RED;
        Color c2 = Color.GREEN;
//        System.out.println(c1);
//        c1.colorInfo();
    }
}

enum Color
{
    RED, GREEN, BLUE;

    // 构造函数
    private Color()
    {
        System.out.println("Constructor called for : " + this.toString());
    }

    public void colorInfo()
    {
        System.out.println("Universal Color");
    }
}