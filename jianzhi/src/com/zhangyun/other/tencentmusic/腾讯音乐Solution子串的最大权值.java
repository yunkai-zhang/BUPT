package com.zhangyun.other.tencentmusic;

import java.util.*;


public class 腾讯音乐Solution子串的最大权值 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param str string字符串 
     * @param k int整型 
     * @return int整型
     */
    public int getMaxValue (String str, int k) {
        // write code here
        int l=str.length();

        Map<Character,Integer> map=new HashMap<>();
        //记录每一个字符出现的次数
        for(int i=0;i<l;i++){
            map.put(str.charAt(i),map.getOrDefault(str.charAt(i),0)+1);

        }

        //可能的最大权值就是：完整的字符串长度*完整的字符串中所有字符的种类数
        int max=map.size()*l;

        //二分查找子串可能的最大权值。0-max之间就是所有可能的权值。
        int left=0,right=max;
        while(left<right){
            int m=(left+right)/2;
            if(valid(m,str,k)){//查看是否能把str切割成k段，并且最大权值不超过m
                right=m;//如果能找到，就right左移，尝试进一步让最大权值尽可能小
            }else{//如果当前权值m无法实现，尝试放宽对m的要求即增大m；即尝试实现“k个子串的最大权值允许稍微更大一点”
                left=m+1;
            }
        }

        //退出while后的left，就是满足的最小的最大权值
        return left;
    }

    //str能被切割为k段，并且权值都不超过m，就返回true；反之返回false。这里每一段都尽量接近m，使得贪心也能拿到全局最优；因为每一段都接近m会使得切出的段数最少，如果最少段数都超过了给定的k，说明切不了。
    public boolean valid(int m,String str,int k){
        int num=0;//表示字符串切割成的段数

        for(int i=0,j=0;j<str.length()&&i<str.length();){
            int temp=calcu(str,i,j);
            if(temp<=m){//如果ij之间子字符串的权值小于等于传入的权值，移动窗口右边缘
                j++;
            }else {//如果ij之间子字符串的权值大于传入的权值，说明j位置不能进入窗口。切割字符串，此时子字符串数目num加一。并且重新找窗口。
                num++;
                i=j;
            }
        }

        //最后一段子字符串没有在for循环内计数，需要在for循环外部计数
        num++;

        //如果切割的段数小于等于k，那么说明可以切割str为k段，使得k段字符串中最大的权值不超过m。
        return num<=k;
    }

    //返回str在ij位置子字符串的权值
    public int calcu(String str,int i,int j){
        int l=str.length();
        Map<Character,Integer> map=new HashMap<>();
        for(int x=i;x<=j&&x<l;x++){
            map.put(str.charAt(x),1);
        }

        return map.size()*(j-i+1);
    }
}