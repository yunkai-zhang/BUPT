package com.zhangyun.other.antgroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class 蚂蚁Main奇数偶数数字做减法 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        long num=Long.parseLong(br.readLine().trim());

        StringBuilder odd=new StringBuilder();
        StringBuilder even=new StringBuilder();

        while(num!=0){
            int tmp=(int)(num%10);
            if((tmp&1)==1){
                odd.append(tmp);
            }else{
                even.append(tmp);
            }
            num/=10;
        }

        String oddStr=odd.reverse().toString();
        String evenStr=even.reverse().toString();

        //小心没有奇数或偶数的情况
        if(oddStr.isEmpty()&&!evenStr.isEmpty()){
            System.out.println(evenStr);
            return;
        }
        if(evenStr.isEmpty()&&!oddStr.isEmpty()){
            System.out.println(oddStr);
            return;
        }else if(oddStr.isEmpty()&&evenStr.isEmpty()){
            System.out.println("0");
            return;
        }

        BigInteger oddBI=new BigInteger(oddStr);
        BigInteger evenBI=new BigInteger(evenStr);

        BigInteger res= oddBI.subtract(evenBI);
        System.out.println(Math.abs(res.longValue()));
        return;
    }

}
