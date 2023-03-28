package com.zhangyun.other.baidu20230313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1，坛友：
 * 第二题做法也可以是
 * rr     3
 * rree      6
 * rreedd     9
 * rreeddrr      12
 * 然后要是3～6中间的4和5的话
 * 就是
 * rre
 * rred
 * 后面的一样。
 * 我：这种划分粒度就比较细，但是没用上长回文，可能在n比较大的时候超过字符串长度限制。但是坛友这里这么说了，那可能是没超过。
 *
 */

public class 百度Mainx个回文过8成 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int x=Integer.parseInt(br.readLine().trim());

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
        //x-((1+left)*left/2)个回文用red挨个补足
        long stillShort= x-((1+left)*left/2);
        char nextTag='r';
        for(int i=0;i<stillShort;i++){
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
