package com.zhangyun.other.mihayou;
//通过全部测试案例！

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 米哈游Main米小游的色盲视角 {
    static int fullNum=0;
    static int rNum=0;
    public static void main(String[] args) throws IOException {
        /**
         * 使用岛屿沉没法，做两次沉没
         * */
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        String[] firstL= br.readLine().trim().split(" ");
        int n=Integer.parseInt(firstL[0]);
        int m=Integer.parseInt(firstL[1]);

        //记录两个数组，做两次岛屿沉默
        char[][] fullColor=new char[n][m];
        char[][] rColor=new char[n][m];
        for(int i=0;i<n;i++){
            char[] tempch=br.readLine().trim().toCharArray();
            for(int j=0;j<m;j++){
                fullColor[i][j]=tempch[j];
                rColor[i][j]=tempch[j];
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                //尝试登录岛屿，并计数，并把岛屿标记
                if(dfs1(fullColor,i,j,n,m,'R')||dfs1(fullColor,i,j,n,m,'G')||dfs1(fullColor,i,j,n,m,'B'))
                    fullNum++;
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                //尝试登录岛屿，并计数，并把岛屿标记
                if(dfs2(rColor,i,j,n,m,new char[]{'R','R'})||dfs2(rColor,i,j,n,m,new char[]{'G','B'})){
                    rNum++;
                }
            }
        }

        System.out.println(fullNum-rNum);
    }

    public static boolean dfs1(char[][]colors,int i,int j,int n,int m,char curColor){
        //回溯触底，先污染后治理
        if(i<0||i>=n||j<0||j>=m||colors[i][j]!=curColor){
            return false;
        }

        //并标记需要处理的色块
        colors[i][j]='N';

        //把岛屿涂满颜色
        dfs1(colors,i-1,j,n,m,curColor);
        dfs1(colors,i+1,j,n,m,curColor);
        dfs1(colors,i,j-1,n,m,curColor);
        dfs1(colors,i,j+1,n,m,curColor);

        //如果是第一次登录，并且是岛屿，就返回true
        return true;
    }

    public static boolean dfs2(char[][]colors,int i,int j,int n,int m,char[] curColor){
        //回溯触底，先污染后治理
        if(i<0||i>=n||j<0||j>=m){
            return false;
        }
        if(colors[i][j]!=curColor[0]&&colors[i][j]!=curColor[1])return false;
        //走到这，说明当前元素是R，或者是GB。如果curColor是R，那么只会进入R的土地；如果curcolor是GB，那么只会进入GB的土地

        //并标记需要处理的色块
        colors[i][j]='N';

        //把岛屿涂满颜色
        dfs2(colors,i-1,j,n,m,curColor);
        dfs2(colors,i+1,j,n,m,curColor);
        dfs2(colors,i,j-1,n,m,curColor);
        dfs2(colors,i,j+1,n,m,curColor);

        //如果是第一次登录，并且是岛屿，就返回true
        return true;
    }
}
