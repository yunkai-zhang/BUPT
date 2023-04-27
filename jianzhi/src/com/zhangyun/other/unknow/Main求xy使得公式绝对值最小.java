package com.zhangyun.other.unknow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//直接用暴力法写了
public class Main求xy使得公式绝对值最小 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine().trim());

        int min=Integer.MAX_VALUE;
        int xres=-1,yres=-1;
        for(int x=0;x<n;x++){
            if(x==2) continue;

            for (int y=0;y<n;y++){
                if(y==2) continue;

                int temp=cal(x,y);
                int tempmin=Math.abs(temp-n);
                if(tempmin<min){
                    min=tempmin;
                    xres=x;
                    yres=y;
                }
            }
        }

        System.out.println(xres+" "+yres);
    }

    public static int cal(int x,int y){
        int xjie=1;
        while(x>=1){
            xjie*=x;
            x--;
        }

        return (xjie-1)*y;
    }
}
