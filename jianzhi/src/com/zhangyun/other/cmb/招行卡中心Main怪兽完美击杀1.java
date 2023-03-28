package com.zhangyun.other.cmb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 笔试写错了，根据网友提示重新写一遍
 * */
public class 招行卡中心Main怪兽完美击杀1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] line1=br.readLine().trim().split(" ");
        long a=Long.parseLong(line1[0]);
        long b=Long.parseLong(line1[1]);

        //先处理a血量比一次攻击还小的情况，避免分母出现0
        if(a<=b){
            System.out.println(b-a);
            return;
        }

        //拿到a的所有因子
        ArrayList<Long> divisors=new ArrayList<>();
        getDivisors(a,divisors);


        /**
         * 蓝红二分：以a==100，b==23为例
         * 1，蓝色是小于等于23的因数，红色是大于23的因数
         * 2，循环结束时，比较left和right谁离23更近，就让23往它靠拢
         * */
        int left=-1,right=divisors.size();//因为arraylist的坐标不支持long，考虑到10^12大小的数的因数个数不太可能超过2*10^9，所以用
        while(left+1!=right){
            int mid=left+(right-left)/2;
            if(divisors.get(mid)<=b){
                left=mid;
            }else {
                right=mid;
            }
        }
        if(b-divisors.get(left)>divisors.get(right)-b){
            System.out.println(divisors.get(right)-b);
        }else{
            System.out.println(b-divisors.get(left));
        }

    }

    public static void getDivisors(long num,ArrayList<Long> arr){

        //从1遍历到num的平方跟即可。
        //这时你可能会说，那后面的数呢，比如num本身，不是计算不到了嘛？
        //别急，看for循环里面的处理情况

        for ( long i = 1 ; i <= Math.sqrt(num) ; i++ ){

            //如果能被num整除，那肯定是num的因子，毫无疑问
            if ( num % i == 0 ){
                arr.add(i);

                //重点的部分在这里！！！
                //当i能被num整除的情况下，此时i是相对较小的因子，用i求出num另一个较大的因子n
                //因为当i能被num整除时，那么数"num/i"也一定能被num整除
                //不需要再进行重复的计算，这样算法的运行时间大大降低

                long n = num / i;

                //但用i算出另一个较大的因子时，会出现重复的情况
                //例如num = 4,当遍历到2时，算出另一个较大的因子也是2，这就重复了，要判断一下

                if ( n != i ){
                    arr.add(n);
                }

            }
        }

    }


}



