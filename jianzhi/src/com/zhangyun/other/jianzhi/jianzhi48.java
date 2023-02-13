package com.zhangyun.other.jianzhi;

import java.util.HashMap;
import java.util.Map;

public class jianzhi48 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring(new String("abcabcbb")));
    }
    public static int lengthOfLongestSubstring(String s) {
        /**
         从左往右遍历，ij分别为左右重复元素，dpj由dpj-1和i共同决定
         */

        //存储字符元素-位置
        Map<Character,Integer> map=new HashMap<>();
        //j位置为当前元素，i为当前元素之前的重复元素的位置
        int i=-1,dpPre=0,result=0;
        for(int j=0;j<s.length();j++){
            //先获取当前元素的上一个重复元素的index，如果没出现过则返回-1；
            i=map.getOrDefault(s.charAt(j),-1);

            //当前元素为右尾的子串的长度，由dpPre和ij关系共同决定。注意dpPre==j-i时j的上一个重复元素在dpPre对应的子串的首位，所以在dpPre对应的子串内，所以不能用dpPre+1作为新值。
            int dpCur=(dpPre>=j-i?j-i:dpPre+1);
            //比较以当前元素为结尾的子串的最大长度和历史上记录的最大长度，更新历史最大长度
            result=Math.max(result,dpCur);

            //更新s.charAt(j)字符的位置为当前位置
            map.put(s.charAt(j),j);
        }

        return result;

    }
}
