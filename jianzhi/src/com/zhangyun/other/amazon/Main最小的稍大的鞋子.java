package com.zhangyun.other.amazon;

import java.util.Arrays;

public class Main最小的稍大的鞋子 {
    public static void main(String[] args) {
        int[] shoeSizes=new int[]{10,3,1,21,17,18,1000};
        Arrays.sort(shoeSizes);
        System.out.println(query(17,shoeSizes));
    }

    public static int query(int footSize,int[]shoeSizes){
        //先二分查找，找到第一个不小于footsize的尺码
        int left=-1,right=shoeSizes.length;

        while(left+1!=right){
            int mid=left+(right-left)/2;

            if(shoeSizes[mid]<=footSize){
                left=mid;
            }else {
                right=mid;
            }
        }

        if(right== shoeSizes.length)return -1;//找不到
        else return shoeSizes[right];
    }
}
