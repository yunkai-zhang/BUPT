package com.zhangyun.other.meituan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 美团Main修改字符串 {
    public static void main(String[] args) throws IOException {

//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//        String str=br.readLine();
        String str="111111";
        char[] chars = str.toCharArray();

        //处理特殊情况：字符个数小于三个
        if(chars.length==1){
            System.out.println(0);
            return;
        }else if(chars.length==2){
            if(chars[0]==chars[1]) {
                System.out.println(1);
            }else{
                System.out.println(0);
            }
            return;
        }
        //走到这里说明字符数组至少有三个元素，使用滑动窗口来处理
        int sum=0;
        for(int i=2;i<chars.length;i++){
            if(chars[i]==chars[i-1]&&chars[i-2]==chars[i-1]){//三个元素相等，修改第二个元素
                chars[i-1]='x';
                sum++;
            }else if(chars[i-1]==chars[i]){//后两个相等，修改最后一个
                chars[i]='x';
                sum++;
            }else if(chars[i-1]==chars[i-2]){//前两个相等，中间那个（只有开始的时候会出现这个判断）
                chars[i-1]='x';
                sum++;
            }
        }

        System.out.println(sum);
        return;
    }

}
