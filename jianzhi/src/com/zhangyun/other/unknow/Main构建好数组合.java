package com.zhangyun.other.unknow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//当前数比之前的数都大，那么就是好数。够建长度为n，且有k个好数的排列。
public class Main构建好数组合 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] line1=br.readLine().trim().split(" ");
        int n=Integer.parseInt(line1[0]);
        int k=Integer.parseInt(line1[1]);

        //把后k个数，间隔的插在前面
        StringBuilder sb=new StringBuilder();
        int big=n-k+1;
        int small=n-k;
        while(small>=1||big<=n){
            //[n-k+1,n]的数升序插入
            if(big<=n){
                sb.append(big);
                sb.append(" ");
                big++;
            }

            //把[1,n-k]的数倒序插入
            if(small>=1){
                sb.append(small);
                sb.append(" ");
                small--;
            }
        }

        System.out.println(sb.toString().trim());
    }

}
