//package com.zhangyun.other.huawei;
//
//
//// We have imported the necessary tool classes.
//// If you need to import additional packages or classes, please import here.
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        // please define the JAVA input here. For example: Scanner s = new Scanner(System.in);
//        // please finish the function body here.
//        // please define the JAVA output here. For example: System.out.println(s.nextInt());
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//        String[] strs =br.readLine().trim().split(" ");
//        long[] rs=new long[strs.length];
//        for(int i=0;i<rs.length;i++){
//            rs[i]=Long.parseLong(strs[i]);
//        }
//        long cnt=Long.parseLong(br.readLine().trim());
//
//        //处理特殊情况
//        if(strs.length==0){
//            System.out.println(-1);
//            return;
//        }
//
//        //先排序rs，原地构建前缀和数组
//        Arrays.sort(rs);
//        for(int i=1;i<rs.length;i++){
//            rs[i]=rs[i-1]+rs[i-1];
//        }
//
//        //如果不需要限流
//        if(rs[rs.length-1]<=cnt){
//            System.out.println(-1);
//            return;
//        }
//
//        //走到这说明需要限流。因为原数组和现数组都有序，做二分查找
//        int left=-1,right= rs.length;
//        while(left+1!=right){
//            int mid=left+(right-left)/2;
//
//            if(cal(rs,mid)<=cnt){
//                left=mid;
//            }else{
//                right=mid;
//            }
//        }
//        //拿左半边的最后一个，要先判断是不是在界内
//        if(left==-1){
//            //0-第一个数之间找一下
//            int inow=0;
//            for(int i=0;i<rs[0];i++){
//                int temp=rs.length*i;
//                if(temp<=cnt){
//                    inow=i;
//                }else{
//                    break;
//                }
//            }
//
//            System.out.println(inow);//找不到何时的limit能让sum小于cnt
//            return;
//        }
//        if(left==0){
//            //第一个数到第二个数之间找一下
//            long inow=rs[0];
//            for(long i=inow;i<rs[1];i++){
//                long temp=(rs.length-1)*i+rs[0];
//                if(temp<=cnt){
//                    inow=i;
//                }else{
//                    break;
//                }
//            }
//
//            System.out.println(inow);
//            return;
//        }else{
//            //实际值可能更大，在下一个数之前计算。在min到下一个更大的数之间找一下
//            long inow=rs[left];
//            for(long i=inow;i<rs[left+1];i++){
//                long temp=(rs.length-left-1)*i+rs[left];
//                if(temp<=cnt){
//                    inow=i;
//                }else{
//                    break;
//                }
//            }
//
//            System.out.println(inow);
//            return;
//        }
//
//    }
//
//    public static long cal(long[]rs,int mid){
//        //if(mid==0)return rs[mid]+(rs[mid+1]-rs[mid])*(rs.length-mid-1);
//        return rs[mid]+(rs[mid+1]-rs[mid])*(rs.length-mid-1);
//    }
//}
//
