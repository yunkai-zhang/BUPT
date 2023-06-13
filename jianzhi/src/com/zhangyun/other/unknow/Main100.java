package com.zhangyun.other.unknow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main100 {
    public static void main(String[] args) throws IOException {
        /**
         * 使用前缀和的思想
         *
         * 用单向滑动窗口解决
         *
         * 注意相加时使用long
         * */

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine().trim());
        String[] AStrs=br.readLine().trim().split(" ");
        int []A=new int[AStrs.length];
        for(int i=0;i< A.length;i++){
            A[i]=Integer.parseInt(AStrs[i]);
        }
        String[] BStrs=br.readLine().trim().split(" ");
        int []B=new int[BStrs.length];
        for(int i=0;i< B.length;i++){
            B[i]=Integer.parseInt(BStrs[i]);
        }
        String[] LR=br.readLine().trim().split(" ");
        long La=Long.parseLong(LR[0]);
        long Ra=Long.parseLong(LR[1]);
        long Lb=Long.parseLong(LR[2]);
        long Rb=Long.parseLong(LR[3]);

        //用long构建前缀和数组
        long[] ALong=new long[A.length+1];//下标1表示前1个数的和，
        long[] BLong=new long[B.length+1];
        for(int i=0;i<N;i++){
            ALong[i+1]=ALong[i]+A[i];
            BLong[i+1]=BLong[i]+B[i];
        }

        //单向滑动窗口
        int count=0,L=0,R=1;//初始就得各一个，才能有差值
        while(R<=N){
            //计算A在LR之间的和
            long sumA=ALong[R]-ALong[L];
            long sumB=BLong[R]-BLong[L];
            //AB有小于就右移R，AB有大于就右移L
            if(sumA<La||sumB<Lb){
                R++;
            }else if(sumA>Ra||sumB>Rb){
                L++;
                if(L==R) R++;//LR至少相隔1，才能相减后拿到至少一个元素
            }else{//两个sum都在范围内
                count++;

                //先尝试右移R，导致sum超过限度的话放弃右移R而改为右移L
                if(R+1<=N){
                    if((ALong[R+1]-ALong[L]<=Ra)&&(BLong[R+1]-BLong[L]<=Rb)){
                        R++;
                    }else{
                        L++;
                        if(L==R) R++;
                    }
                }else{
                    L++;
                    if(L==R) R++;
                }
            }
        }

        System.out.println(count);
    }
}
