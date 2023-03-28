package com.zhangyun.other.tencent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 暴力枚举子数组，看乘积和异或是否相等。a了30%
 * */
public class Main乘积与异或 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine().trim());
        String[] AStr=br.readLine().trim().split(" ");
        int[] A=new int[n];
        for (int i=0;i<n;i++){
            A[i]=Integer.parseInt(AStr[i]);
        }

        //暴力枚举子区间。看[i,j]是否符合
        int res=0;
        for(int i=0;i<n;i++){
            for (int j=i;j<n;j++){
                if(ok(i,j,A)) res++;
            }
        }

        System.out.println(res);
    }

    public static boolean ok(int i,int j,int[] A){
        int mul=1;
        int yihuo=0;
        while(i<=j){
            mul*=A[i];
            yihuo^=A[i];
            i++;
        }

        return mul==yihuo;
    }
}
