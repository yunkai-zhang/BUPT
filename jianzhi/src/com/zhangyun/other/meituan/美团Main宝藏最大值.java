package com.zhangyun.other.meituan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 美团Main宝藏最大值 {
    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        //读取nmk
        String[] input1=br.readLine().trim().split(" ");
        int n=Integer.parseInt(input1[0]);
        int m=Integer.parseInt(input1[1]);
        int k=Integer.parseInt(input1[2]);

        //读取地图颜色，一共有n
        char[][] color=new char[n][m];
        for(int i=0;i<n;i++){
            char[]temp= br.readLine().trim().toCharArray();
            for(int j=0;j<m;j++){
                color[i][j]=temp[j];
            }
        }

        //读取地图金币，一共n行
        int[][] coin=new int[n][m];
        for(int i=0;i<n;i++){
            String[]temp= br.readLine().trim().split(" ");
            for(int j=0;j<m;j++){
                coin[i][j]=Integer.parseInt(temp[j]);
            }
        }

        //动态规划，直接在coin上做。注意任何时候不允许当前金币为负数
        int max=0;

        //先处理第一列
        int marki=0;
        boolean markColumn=false;
        for(int i=1;i<n;i++){
            if(color[i][0]==color[i-1][0]){
                coin[i][0]=(coin[i][0]+coin[i-1][0]);
                max=Math.max(max,coin[i][0]);
            }else if((color[i][0]!=color[i-1][0])&&(coin[i][0]+coin[i-1][0]-k>=0)){//如果允许往下走
                coin[i][0]=(coin[i][0]+coin[i-1][0]-k);
                max=Math.max(max,coin[i][0]);
            }else{//本节点和下面的节点都不可访问，标记颜色
                markColumn=true;
                marki=i;
            }
        }
        if(markColumn){
            for(int i=marki;i<n;i++){
                color[i][0]='N';
            }
        }

        //再处理第一行
        int markj=0;
        boolean markRow=false;
        for(int j=1;j<m;j++){
            if(color[0][j]==color[0][j-1]){
                coin[0][j]=(coin[0][j]+coin[0][j-1]);
                max=Math.max(max,coin[0][j]);
            }else if((color[0][j]!=color[0][j-1])&&(coin[0][j]+coin[0][j-1]-k>=0)){//如果允许往右走
                coin[0][j]=(coin[0][j]+coin[0][j-1]-k);
                max=Math.max(max,coin[0][j]);
            }else{//本节点和右面的节点都不可访问，标记颜色
                markRow=true;
                markj=j;
            }
        }
        if(markRow){
            for(int j=markj;j<m;j++){
                color[0][j]='N';
            }
        }

        //从[1][1]位置开始处理
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                //尝试从左往右移动
                boolean fromLeft=true;
                int fromLeftV=0;
                if(color[i][j-1]!='N'){//如果允许往右走
                    if(color[i][j]==color[i][j-1]){
                        fromLeftV=coin[i][j-1];
//                        coin[i][j]+=(coin[i][j]+coin[i][j-1]);
//                        max=Math.max(max,coin[i][j]);
                    }else if((color[i][j]!=color[i][j-1])&&(coin[i][j]+coin[i][j-1]-k>=0)){//如果允许往右走
                        fromLeftV=coin[i][j-1]-k;
//                        coin[i][j]+=(coin[i][j]+coin[i][j-1]-k);
//                        max=Math.max(max,coin[i][j]);
                    }else{
                        fromLeft=false;
                    }
                }else{
                    fromLeft=false;
                }

                //尝试从上往下移动
                boolean fromUp=true;
                int fromUpV=0;
                if(color[i-1][j]!='N'){//如果允许往下移动
                    if(color[i][j]==color[i-1][j]){
                        fromUpV=coin[i-1][j];
//                        coin[i][j]+=(coin[i][j]+coin[i-1][j]);
//                        max=Math.max(max,coin[i][j]);
                    }else if((color[i][j]!=color[i-1][j])&&(coin[i][j]+coin[i-1][j]-k>=0)){//如果允许往下走
                        fromUpV=coin[i-1][j]-k;
//                        coin[i][j]+=(coin[i][j]+coin[i-1][j]-k);
//                        max=Math.max(max,coin[i][j]);
                    }else{
                        fromUp=false;
                    }
                }else {
                    fromUp=false;
                }

                //看看能不能走
                if(fromLeft||fromUp){
                    if(fromLeft&&fromUp){//两边都可以走
                        coin[i][j]+=Math.max(fromLeftV,fromUpV);
                    }else if(fromLeft){//可以从左边走
                        coin[i][j]=fromLeftV;
                    }else{//可以从上边走
                        coin[i][j]=fromUpV;
                    }
                    max=Math.max(max,coin[i][j]);
                }else{//当前节点不能走
                    color[0][j]='N';
                }


            }
        }

        System.out.println(max);
        return;

    }
}

