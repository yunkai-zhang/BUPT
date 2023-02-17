package com.zhangyun.other.momenta;

public class Main {
    public static void main(String[] args){
        int[] nums=new int[]{4,1,2,2,3};

        Main main=new Main();
        main.quickSort(nums,0, nums.length-1);

        for(int i:nums){
            System.out.println(i);
        }


    }

    public void quickSort(int[] nums,int l,int r){

        //递归排序
        if(l<r){
            int mid=getMid(nums,l,r);
            quickSort(nums,l,mid-1);
            quickSort(nums,mid+1,r);
        }

    }
    public int getMid(int[]nums,int l,int r){
        //选取最左侧元素作为target
        int target=nums[l];

        //排序l到r，保证target左侧都不比它大，右侧都不比它小
        while(l<r){
            //先移动右边
            while(l<r&&nums[r]>=target) r--;
            swap(nums,l,r);
            //再移动左边
            while(l<r&&nums[l]<=target) l++;
            swap(nums,l,r);
        }
        nums[r]=target;

        return r;
    }

    public void swap(int[]nums,int l,int r){
        int temp=nums[l];
        nums[l]=nums[r];
        nums[r]=temp;
    }
}
