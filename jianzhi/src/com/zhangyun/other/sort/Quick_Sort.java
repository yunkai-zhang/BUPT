package com.zhangyun.other.sort;

public class Quick_Sort {
    public static void main(String[] args){
        int[] nums=new int[]{3,8,1,4,2,7,5,6};//直接赋值初始化数组的话，不需要在new int[]的[]中指定数组的大小

        quick_sort(nums,0,nums.length-1);

        myPrint(nums);
    }

    //把传入数组的分割成左小右大的情况，中间点
    public static int getMid(int[] nums,int left,int right){
        //选定好用作pivot的元素，可以是[left,right]内任意位置的元素。这个pivot相当于是一个坑，坑要不停拆东墙补西墙去填平。
        int target=nums[left];
        while(left<right){
            //先移动右指针，找到第一个比target小的元素
            while(left<right&&target<=nums[right])right--;
            //拿right位置的元素填平left位置的坑。那么right就是新的坑的位置
            nums[left]=nums[right];//不需要判断left<right再赋值，因为及时上面的循环是因为left==right退出的，这里赋值相当于自我赋值，不影响结果

            //再移动左指针，找到第一个比pivot大的元素，填入right的坑中
            while(left<right&&target>=nums[left])left++;
            //拿left位置的元素填平right位置的坑。那么left位置就是新的坑的位置
            nums[right]=nums[left];

        }
        //退出while循环时，left==right，此时把之前找的用作pivot的元素的值填入left和right重合的此位置
        nums[left]=target;

        return left;
    }
    public static void quick_sort(int[] nums,int left,int right){
        //[left,right]区间内，有至少两个元素时，才会进行递归快排
        if(left<right){
            int i=getMid(nums,left,right);
            quick_sort(nums,left,i-1);
            quick_sort(nums,i+1,right);
        }
    }

    public static void myPrint(int[] nums){
        for(int num:nums){
            System.out.print(num+" ");
        }
        //换行
        System.out.println("");
    }
}
