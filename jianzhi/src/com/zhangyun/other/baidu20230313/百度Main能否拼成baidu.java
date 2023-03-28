package com.zhangyun.other.baidu20230313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 百度Main能否拼成baidu {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int rownum=Integer.parseInt(br.readLine().trim());
        StringBuilder sb=new StringBuilder();
        int[] baidu=new int[5];
        int baiduNum=0;

        //读取每行字符串
        for(int i=0;i<rownum;i++){
            char[] curStrInCh=br.readLine().trim().toCharArray();
            if(curStrInCh.length==5){
                //赋值
                for(char ch:curStrInCh){
                    if(ch=='B')baidu[0]=1;
                    else if(ch=='a')baidu[1]=1;
                    else if(ch=='i')baidu[2]=1;
                    else if(ch=='d')baidu[3]=1;
                    else if(ch=='u')baidu[4]=1;
                }
                //检查，根据检查结果打印，并还原值
                for(int j=0;j<baidu.length;j++){
                    if(baidu[j]!=0)baiduNum++;
                    baidu[j]=0;
                }
                if(baiduNum==5){
                    System.out.println("Yes");
                }else{
                    System.out.println("No");
                }
                baiduNum=0;
            }else{
                System.out.println("No");
            }
        }
    }
}
