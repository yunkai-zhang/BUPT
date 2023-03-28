package com.zhangyun.other.xiaohongshu;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 小红书暑期Main对窗口内的数组做操作 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine().trim());
        String[] arrayStr=br.readLine().trim().split(" ");
        int[] array=new int[arrayStr.length];//拿到整数格式的数组，方便后续处理
        for(int i=0;i<arrayStr.length;i++){
            array[i]=Integer.parseInt(arrayStr[i]);
        }
        int M=Integer.parseInt(br.readLine().trim());
        String[] Ls=br.readLine().trim().split(" ");
        String[] Rs=br.readLine().trim().split(" ");
        char[] ops=br.readLine().trim().toCharArray();
        String[] XsStr=br.readLine().trim().split(" ");
        int[] Xs=new int[XsStr.length];//拿到整数格式的数组，方便后续处理
        for(int i=0;i<XsStr.length;i++){
            Xs[i]=Integer.parseInt(XsStr[i]);
        }

        //针对M次操作做处理
        for(int i=0;i<M;i++){
            int left=Integer.parseInt(Ls[i])-1;//LR是从1开始的，所以要作用于数组下标的话要减一
            int right=Integer.parseInt(Rs[i])-1;
            char curOp=ops[i];
            //把区间中的每个元素做操作
            while(left<=right){
                if(curOp=='|'){
                    array[left] |= Xs[i];
                }else if(curOp=='&'){
                    array[left] &= Xs[i];
                }else if(curOp=='='){//网友说，先判断`=`可以用java暴力a出来
                    array[left] = Xs[i];
                }
                left++;

            }
        }

        StringBuilder sb=new StringBuilder();
        for (int i=0;i<array.length;i++){
            sb.append(array[i]);
            sb.append(" ");
        }
        System.out.println(sb.toString().trim());


    }
}

