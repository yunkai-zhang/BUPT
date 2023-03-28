package com.zhangyun.other.xiaohongshu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 小红书暑期Main解码 {

    public static void main(String[] args) throws IOException {

        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine().trim());
        char[] line2Ch=br.readLine().trim().toCharArray();

        for(int i=0;i<line2Ch.length;i++){//用for(:)的方法遍历数组是没办法修改数组
            if(line2Ch[i]=='a'){
                line2Ch[i]='x';
            }else if(line2Ch[i]=='b'){
                line2Ch[i]='y';
            }else if(line2Ch[i]=='c'){
                line2Ch[i]='z';
            }else{
                line2Ch[i]-=3;
            }
        }

        System.out.println(String.valueOf(line2Ch));

    }
}
