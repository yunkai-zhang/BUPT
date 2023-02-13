package com.zhangyun.other.jianzhi;

public class jianzhi11 {

    public static void main(String[] args) {
        int [] num=new int[]{-9,10,-10,-9};
        System.out.println(minArray(num));
    }

    public static int minArray(int[] numbers) {

        int last=0;
        for(int i=0;i<numbers.length;i++){
            //侦查到断崖就返回
            if(numbers[i]<last){
                return numbers[i];
            }
            last=numbers[i];
        }

        //没侦查到断崖，可能是数字全相同；或者经历了n次旋转成了没旋转前的升序状态：返回第一个数
        return numbers[0];
    }
}
