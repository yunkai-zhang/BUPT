package com.zhangyun.other.pinduoduo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 拼多多Main求流数据的平均数和中位数 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int daynum=Integer.parseInt(br.readLine().trim());
        String[] cusnum=br.readLine().trim().split(" ");

        StringBuilder ave=new StringBuilder();
        StringBuilder mid=new StringBuilder();

        //使用除法计算平均数
        //使用大小顶堆计算中位数
        PriorityQueue<Integer> smaller=new PriorityQueue<>((o1, o2) -> (o2-o1));//大顶堆,允许多一个数
        PriorityQueue<Integer> bigger=new PriorityQueue<>();//小顶堆

        //!!!注意：这里要用long，用int会导致只能过66%的测试案例
        int sum=0;
        if(daynum==0){
            ave.append(0);
            mid.append(0);
        }else{
            for(int i=0;i<cusnum.length;i++){
                int curnum=Integer.parseInt(cusnum[i]);
                sum+=curnum;
                if(smaller.size()==bigger.size()){
                    bigger.offer(curnum);
                    smaller.offer(bigger.poll());
                }else{
                    smaller.offer(curnum);
                    bigger.offer(smaller.poll());
                }

                //计算平均数
                double ave1=sum/(i+1.0);
                String ave1str=ave1+"";
                if(ave1str.charAt(ave1str.lastIndexOf(".")+1)-'0'>=5){//可以用Math.round()实现四舍五入
                    ave.append((sum/(i+1)+1)+" ");
                }else{
                    ave.append((sum/(i+1))+" ");
                }

                //计算中位数
                if(smaller.size()==bigger.size()){
                    int temp1= bigger.peek();
                    int temp2=smaller.peek();
                    double mid1=(temp1+temp2)/2.0;
                    String mid1str=mid1+"";
                    if(mid1str.charAt(mid1str.lastIndexOf(".")+1)-'0'>=5){
                        mid.append(((temp1+temp2)/2+1)+" ");
                    }else{
                        mid.append(((temp1+temp2)/2)+" ");
                    }
                }else{
                    mid.append(smaller.peek()+" ");
                }
        }

        }

        System.out.println(ave.toString().trim());
        System.out.println(mid.toString().trim());



    }
}

