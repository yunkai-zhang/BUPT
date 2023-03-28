package com.zhangyun.other.tencentmusic;

import java.util.*;


public class 腾讯音乐Solution小红的字符串 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param str string字符串
     * @return int整型
     */
    public static int getCnt (String str) {
        // write code here

        if(str.length()<2)return 0;

        int gapStd='a'-'A';
        int count=0;
        //使用一个长度为2的滑动窗口。i是窗口的右边缘。
        for(int i=1;i<str.length();i++){
            char chPre=str.charAt(i-1);
            char chCur=str.charAt(i);
            int gap= Math.abs(chPre-chCur);
            if(gapStd==gap||chPre==chCur){
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(getCnt("aABbbC"));
    }
}
