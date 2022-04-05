package com.zhangyun.others;

public class jianzhi13_sum {
    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            for(int j=0;j<100;j++){
                if(method1(i,j)!=method2(i,j))
                    System.out.print("i:"+i+" j:"+j+" ");
            }
        }
    }
    static int method1(int i,int j){
        int isum=0,jsum=0;
        while(i/10>0){
            int temp=i%10;
            isum+=temp;
            i/=10;
        }
        //单独处理一下m/10==0时的个位数的m
        isum+=i;
        while(j/10>0){
            int temp=j%10;
            jsum+=temp;
            j/=10;
        }
        //单独处理一下m/10==0时的个位数的m
        jsum+=j;

         return isum+jsum;
    }
   static int method2(int i,int j){
        return i/10 + i%10 + j/10 + j%10;
    }

}
