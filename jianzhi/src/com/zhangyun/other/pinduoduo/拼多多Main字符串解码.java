package com.zhangyun.other.pinduoduo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 拼多多Main字符串解码 {
    static String num="";
    static String num4add="";

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str=br.readLine().trim();
        int left=0,right=0;
        StringBuilder sb=new StringBuilder();
        while(right<str.length()){
            //先移动指针到对应位置
            while(str.charAt(right)<='9'&&str.charAt(right)>='0'){
                right++;
            }
            //截取数字部分
            num=str.substring(left,right);//左闭右开
            num4add="0";
            //截取字母部分
            String c=str.substring(right,right+1);
            while(strCanDecre()){
                sb.append(c);
            }
            sb.append(c);

            //处理完毕后，移动指针
            left=right+1;
            right=left;
        }
        System.out.println(sb.toString());
    }

    //如果大于0，就减少1返回true
    public static boolean strCanDecre(){
        //对num4add做字符串加法，直到两者相等
        StringBuilder sb=new StringBuilder();
        int carry=1;
        for(int i=num4add.length()-1;i>=0;i--){
            int cur=num4add.charAt(i)-'0';
            cur+=carry;
            if(cur>=10){
                carry=1;
                cur-=10;
            }else{
                carry=0;
            }
            sb.append(cur);
        }
        if (carry==1)sb.append(carry);
        num4add=sb.reverse().toString();
        if(!num4add.equals(num)){
            return true;
        }else{
            return false;
        }
    }
}
