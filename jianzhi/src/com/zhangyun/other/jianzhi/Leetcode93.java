package com.zhangyun.other.jianzhi;

import java.util.ArrayList;
import java.util.List;

public class Leetcode93 {
    static List<String> res;
    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("25525511135"));
    }


    public static List<String> restoreIpAddresses(String s) {
        /**
         回溯
         1，SB记录已经构成的合法的字符串部分
         2，使用segnum记录当前递归的深度，如果是第四层递归，要不加入结果集，要不抛弃。
         3，每一层取s剩余字符串的1-3个字符，如果字符以0开头并长度大于2，就continue结束当前循环
         */

        res=new ArrayList<>();

        recur(0, s, "",0);

        return res;
    }

    public static void recur(int cur, String s ,String getStr,int hasSeg){
        //因为字符串隐式回溯的特性，hashseg满了后无法进入for循环，所以得在for循环外面尝试收割。我也是idea做debug发现的，才这么写的
        if(hasSeg==4){
            if(cur!=s.length()){//如果四段ip都有了，但是s还没处理完，说明当前ip不是合理的划分
                return;
            }else {//收割
                res.add(getStr.substring(0,getStr.length() - 1));
            }
        }

        //分别拿到[cur,cur] [cur,cur+1] [cur,cur+2]的字符串作为当前元素并处理
        for(int i=cur;i<s.length()&&i<cur+3;i++){
            //获取合法的ip段temp
            String temp=s.substring(cur,i+1);//左闭右开
            if(temp.charAt(0)=='0'&&temp.length()>1)continue;
            if(Integer.parseInt(temp)-Integer.parseInt("255")>0) continue;//!!!compareTo是从前往后按照字典顺序比较大小，长的字符串会更大。并不会直接返回两个数字类型的字符串的相对大小（除非两个数字字符串长度相同）

            //走到这说明，目前一切正常


            //递归处理
            recur(i+1,s,getStr+temp+".",hasSeg+1);
            //sb.substring(0,sb.length()-temp.length()-1);这个函数返回的是字符串，并不能起到回溯sb的作用。所以考虑使用string直接隐式回溯，因为字符串相加会生成新字符串


        }
    }
}
