package com.zhangyun.other.unknow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main数字中零的个数 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line1=br.readLine().trim();

        int res=0;
        for(int i=0;i<line1.length();i++){
            char ch=line1.charAt(i);
            if(ch=='0'||ch=='6'||ch=='9')res++;
            else if(ch=='8') res+=2;
        }

        System.out.println(res);
    }
}
