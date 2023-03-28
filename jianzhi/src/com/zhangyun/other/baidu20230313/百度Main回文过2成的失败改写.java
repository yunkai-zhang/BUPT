package com.zhangyun.other.baidu20230313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 百度Main回文过2成的失败改写 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        long x=Long.parseLong(br.readLine().trim());

        //处理无法二分的特殊情况，即n>x/2
        if(x==1){
            System.out.println("r");
        }else if(x==2){
            System.out.println("re");
        }else if(x==3){
            System.out.println("red");
        }else if(x==4){
            System.out.println("redr");
        }else if(x==5){
            System.out.println("redre");
        }

        //二分查找，找到(1+n)*n/2 < x时的n，那么就打印n个相同字母“d”，最后补上若干个red
        long left=0,right=x/2+1;
        while(left!=right-1){
            long mid=left+(right-left)/2;
            long temp=(1+mid)*mid/2;
            if(temp>x) right=mid;
            else left=mid;
        }
        //退出的时候，left就 是第一个使得temp不大于x的值了
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<left;i++){
            sb.append("d");
        }

        //x-((1+left)*left/2)个回文，再尝试用e填充一下
        long stillShort= x-((1+left)*left/2);
        long left1=0,right1=stillShort/2+1;
        while(left1!=right1-1){
            long mid=left1+(right1-left1)/2;
            long temp=(1+mid)*mid/2;
            if(temp>x) right1=mid;
            else left1=mid;
        }
        for(int i=0;i<left1;i++){
            sb.insert(0,"e");
        }

        //剩下的回文用red挨个补足
        long stillShort1=stillShort-((1+left1)*left1/2);
        char nextTag='r';
        for(int i=0;i<stillShort1;i++){
            if(nextTag=='r'){
                sb.append("r");
                nextTag='e';
            }else if(nextTag=='e'){
                sb.append("e");
                nextTag='d';
            }else if(nextTag=='d'){
                sb.append("d");
                nextTag='r';
            }
        }

        System.out.println(sb.toString());
    }
}



