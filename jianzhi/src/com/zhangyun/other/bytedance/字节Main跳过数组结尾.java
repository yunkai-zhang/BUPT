package com.zhangyun.other.bytedance;

import java.util.Scanner;

/**
 * 题目：给一段数组，初始所在位置是0，在某位置上最远可以跳nums[i]步；判断能不能跳过数组末尾
 * 例如：[3 2 1 0 4]都会停在index==3的位置，跳不过结尾。[1 2 3 3]可以跳到结尾。
 *
 * 思考：所有节点都落到0上就会跳不过去。临时没有更好地思路，先用回溯把题目a了。
 * */
public class 字节Main跳过数组结尾 {
    static boolean ok=false;
    public static void main(String[] args) {
        // Scanner input=new Scanner(System.in);
        // String str=input.next();
        //System.out.println("hello world");

        int[] nums=new int[]{3,2,1,0,4};
        backtrack(nums, 0, nums[0]);

        if(ok){
            System.out.println(true);
        }else{
            System.out.println(false);
        }
    }

    //在左闭右闭的区间
    public static void backtrack(int[]nums,int start,int end){
        if(ok){
            return;
        }else if(end>=nums.length){
            ok=true;
            return;
        }

        //从最远的地方开始尝试，不行就回溯
        for(int i=end;i>start;i--){
            //当前位置跳不动的时候就放弃
            if(nums[i]==0)continue;

            backtrack(nums, i, nums[i]);
        }
    }
}