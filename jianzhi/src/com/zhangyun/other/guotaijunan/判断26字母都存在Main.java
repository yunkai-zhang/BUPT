package com.zhangyun.other.guotaijunan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 判断26字母都存在Main {
    public static void main(String[] args) throws IOException {
        int[] charMark=new int[26];

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        char[] chs = br.readLine().toCharArray();

        for(char ch:chs){
            charMark[ch-'a']=1;
        }

        for(int i:charMark){
            if(i==0) {
                System.out.println(0);
                return;
            }
        }

        System.out.println(1);
    }
}
