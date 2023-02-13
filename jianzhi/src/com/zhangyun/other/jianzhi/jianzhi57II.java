package com.zhangyun.other.jianzhi;

import java.util.ArrayList;
import java.util.List;

public class jianzhi57II {
    public static void main(String[] args) {
        System.out.println(findContinuousSequence(50252).length);
    }
    public static int[][] findContinuousSequence(int target) {
        /**
         等差数列求和公式+while双指针
         */
        List<List<Integer>> resultList =new ArrayList<>();
        List<Integer> oneTempInstance=null;
        //以每个整数为left开始做双指针
        for(int i=1;i<target;i++){
            int left=i,right=target;
            //做双指针
            while(left<right){
                long temp1=(left+right)*(right-left+1),temp2=target*2;//避免反复计算.除法默认向下取整，尽量避免
                if(temp1==temp2){//找到目标时。不会再在本基础上缩小范围了，用break跳出while避免无效计算
                    oneTempInstance=new ArrayList<>();
                    for(int j=left;j<=right;j++){
                        oneTempInstance.add(j);
                    }
                    resultList.add(new ArrayList<>(oneTempInstance));//为了避免oneInstance的操作影响resultList中已存入的结果，根据oneTempInstance的内容新分配一块内存作为resultList的元素
                    break;
                }else if(temp1<temp2){
                    left++;
                }else{
                    right--;
                }
            }
        }

        //把列表转化为2维数组返回。转换成数组是真傻比，直接返回链表不就行了！
        int[][] result=new int[resultList.size()][];//第一个方括号表示行，应该在这个括号限定行数
        for(int i=0;i<resultList.size();i++){
            //复用oneTempInstance
            oneTempInstance=resultList.get(i);
            int tempSize=oneTempInstance.size();
            //对二维数组不等长的情况分配内存：https://blog.csdn.net/weixin_30709179/article/details/115082821
            result[i]=new int[tempSize];
            for(int j=0;j<tempSize;j++){
                result[i][j]=oneTempInstance.get(j);
            }
        }
        return result;
    }
}
