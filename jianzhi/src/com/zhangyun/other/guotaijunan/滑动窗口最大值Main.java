package com.zhangyun.other.guotaijunan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class 滑动窗口最大值Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] arrayString = br.readLine().split(" ");
        int[] array=new int[arrayString.length];
        for(int i=0;i<arrayString.length;i++){
            array[i]=Integer.parseInt(arrayString[i]);
        }
        String windowSizeStr = br.readLine();
        int windowSize=Integer.parseInt(windowSizeStr);

        //使用降序队列
        Deque<Integer> deque=new LinkedList<>();

        //先填充整个窗口
        int i=0;
        for(;i<windowSize;i++){
            //删掉队列尾部中小的
            while(!deque.isEmpty()&&deque.peekLast()<array[i]){
                deque.pollLast();
            }
            //队列尾部加入本元素
            deque.offerLast(array[i]);
        }

        //往结果列表中加入当前 deque的头节点
        ArrayList<Integer> arrayList=new ArrayList<>();
        arrayList.add(deque.peekFirst());

        //i为窗口的右边界
        for(;i< array.length;i++){
            //左边先出去
            if(array[i-windowSize]==deque.peekFirst()) deque.pollFirst();

            //右边进来
            //删掉队列尾部中小的
            while(!deque.isEmpty()&&deque.peekLast()<array[i]){
                deque.pollLast();
            }
            //队列尾部加入本元素
            deque.offerLast(array[i]);

            //往结果列表中加入当前 deque的头节点
            arrayList.add(deque.peekFirst());
        }

        //打印结果列表
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        for(int cur:arrayList){
            sb.append(cur+", ");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        System.out.println(sb);

 //               [4, 5, 6, 6, 4]

    }
}
