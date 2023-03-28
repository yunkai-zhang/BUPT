package com.zhangyun.other.jianzhi;

public class BinarySearch {
    public static int mySqrt(int x) {

        //处理平方根大于x的特殊情况。这两种情况下不能用二分查找，所以要先特殊处理
        if(x==0)return 0;
        if(x==1)return 1;
        //走到这里说明x的平方根，都不比x/2大了。使用二分查找找到平方根位置的整数

        /**
         使用蓝红边界法做二分查找
         1，左半边蓝色区域条件：m^2<=x。右半边即为红色区域
         2，返回蓝色区域的最后一个元素即退出循环时的l
         */
        long low=-1,high=x;
        high++;//直接high=x+1会导致x+1溢出整数边界，应该先把数据范围扩充到long再加一
        while (low+1 != high) {
            long mid = low + (high - low) / 2;
            if (mid <= x/mid) low = mid;//防止长整数相乘溢出long
            else high = mid;
        }
        return (int) low;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(2147483647));
    }
}
