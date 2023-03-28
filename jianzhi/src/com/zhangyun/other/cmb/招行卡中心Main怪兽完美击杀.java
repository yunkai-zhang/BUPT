package com.zhangyun.other.cmb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 招行卡中心Main怪兽完美击杀 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] line1=br.readLine().trim().split(" ");
        long a=Long.parseLong(line1[0]);
        long b=Long.parseLong(line1[1]);

        //先处理a血量比一次攻击还小的情况，避免分母出现0
        if(a<=b){
            System.out.println(b-a);
            return;
        }

        //求得商左右的两个数
        long divide=a/b;
        long divider1=a/(divide);
        long divider2=a/(divide+1);

        //看商离左边的整除数更近，还是离右边的整除数更近
        long short1=Math.abs(divider1-b);
        long short2=Math.abs(b-divider2);
        if(short1<short2){
            System.out.println(short1);
        }else{
            System.out.println(short2);
        }
        return;
    }


}



