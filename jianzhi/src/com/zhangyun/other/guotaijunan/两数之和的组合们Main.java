package com.zhangyun.other.guotaijunan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 两数之和的组合们Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] arrayString = br.readLine().split(",");
        int[] array=new int[arrayString.length];
        for(int i=0;i<arrayString.length;i++){
            array[i]=Integer.parseInt(arrayString[i]);
        }
        String sumStr = br.readLine();
        int sum=Integer.parseInt(sumStr);

        //先排序
        Arrays.sort(array);

        //构建结果列表
        List<List<Integer>> res=new ArrayList<>();

        //双指针从两端拿
        int left=0,right=array.length-1;

        while(left<right){
            if(array[left]+array[right]==sum){
                List<Integer>temp=new ArrayList<>();
                temp.add(array[left]);
                temp.add(array[right]);
                res.add(temp);
                left++;
                right--;
            }else if(array[left]+array[right]>sum){
                right--;
            }else {
                left++;
            }


        }

        //打印
        //处理特殊情况，防止65行删除超过数组边界
        if(res.size()==0){
            System.out.println("[]");//！！！题目说输出空，不能 System.out.println("")，而要打印空的"[]"
            return;
        }
        //res不为空，可以安心deletecharat
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        for(int i=0;i<res.size();i++){
            sb.append("[");
            for(int j=0;j<2;j++){
                sb.append(res.get(i).get(j)+", ");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.deleteCharAt(sb.length()-1);
            sb.append("], ");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);//65行
        sb.append("]");
               // [[5, 6], [5, 6]]
        System.out.println(sb);

    }
}
