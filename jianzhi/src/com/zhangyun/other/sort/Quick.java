package com.zhangyun.other.sort;

//公众号：一只快活的野指针
public class Quick {
    public static void main(String[] args) {
        int[] arr={2,8,4,5,7,1,3,6};//直接复制数组
        quick_sort(arr,0,arr.length-1);
        print(arr);
    }
    private static int get_mid(int arr[],int left,int right){
        int pivot=arr[left];//自定义排序中心轴，这里把arr[left]存到pivot中去，此时arr[left]为空。pivot相当于一个中间量
        while(left<right){//当left与right指针相遇的时候退出循环，双指针遍历结束
            while(arr[right]>=pivot && left<right) right--;//right指针从右往左遍历，当arr[right]>=pivot，即满足以pivot为中轴，小放左，大放右的条件时，right指针继续往右遍历。当arr[right]<pivotd的时候，把当前值arr[right]赋给空置arr[left]，此时arr[right]成了空值。
            arr[left]=arr[right];
            while(arr[left]<=pivot && left<right) left++;//到left指针从左往右遍历，当arr[left]<=pivot，即满足以pivot为中轴，小放左，大放右的条件时，left指针继续往左遍历。当arr[left]>pivot的时候，把当前值arr[left]赋给空置arr[right]，此时arr[left]成了空值。
            arr[right]=arr[left];
        }
        //经历了上面的循环实现了pivot为中轴，小放左，大放右的格局
        arr[left]=pivot;//最后把存放在pivot值放回数组空arr[left]中
        return left;//返回中轴所在的下标位置。
    }

    private static void  quick_sort(int[] arr,int left,int right){
        if(left<right){
            /*将arr[left..right]均分为两部分arr[left..mid]和arr[mid+1..right]
             * ,以pivot为中轴，小放左，大放右。这是第一步。*/
            int mid =get_mid(arr,left,right);//接收中轴所在的下标位置。
            quick_sort(arr,left,mid-1);//递归地对arr[left..mid]进行快速排序，使得左子序列有序
            quick_sort(arr,mid+1,right);//递归地对arr[mid+1..right]进行快速排序，使得左子序列有序
        }
    }

    public static void print(int arr[])//封装函打印函数
    {
        for(int k=0;k<arr.length;k++)
        {
            System.out.print(arr[k]+" ");
        }
    }
}
