package com.zhangyun.other.jianzhi;

public class jianzhi13 {
    public static void main(String[] args) {
        //System.out.println(movingCount(11,8,16));
        int m=11,n=1,k=19;

        int [][] board1=new int[m][n];
        int [][] board2=new int[m][n];
        recur1(board1,m,n,k,0,0);
        recur2(board2,m,n,k,0,0);

        //得到可被到达的数目
        int result1=0;
        int result2=0;
        //board1和board2大小一致
        for(int i=0;i<board1.length;i++){
            for(int j=0;j<board1[0].length;j++){
                if(board1[i][j]==1){
                    result1++;
                }
                if(board2[i][j]==1){
                    result2++;
                }
                if(board1[i][j]!=board2[i][j]){

                    System.out.print("board1["+i+"]["+j+"]=="+board1[i][j]);
                    System.out.println("board2["+i+"]["+j+"]=="+board2[i][j]);
                }
            }
        }
        System.out.println("=========");
        System.out.println("result1:"+result1);
        System.out.println("result2:"+result2);


    }

    public static void recur1(int [][] board,int m,int n,int k,int i, int j){
        //System.out.println("进入了recur，此时i:"+i+" j:"+j);
        //下标不符合要求的，直接返回
        if(i<0||i>m-1||j<0||j>n-1){
            //System.out.println("下标不对");
            return;
        }
        //如果已经标记过，说明早被处理过的，直接返回
        if(board[i][j]==1||board[i][j]==-1){
            //System.out.println("被处理过");
            return;
        }

        //如果没有被标记过，则处理本元素
        //记录数位之和
        int isum=0,jsum=0;
        while(i/10>0){
            int temp=i%10;
            isum+=temp;
            i/=10;
        }
        //单独处理一下m/10==0时的个位数的m
        isum+=i;
        while(j/10>0){
            int temp=j%10;
            jsum+=temp;
            j/=10;
        }
        //单独处理一下m/10==0时的个位数的m
        jsum+=j;
        //根据数位之和做标记
        if(isum+jsum>k){
            //System.out.println("这个不是");
            board[i][j]=-1;
            //忘了在不合法的时候return
            return;
        }else{
            //System.out.println("找到了");
            /*
            * ！！！！我这里赋参数的i不是传进来的i，因为中间执行了i自除！！
            * 所以出现了m>10就会发生不一致，因为m>10后i最大就会超过9，即i会大于等于10，就会出发i自除，就会导致board填数错误。
            * 这也就解释了：求数位和的算法没错，为什么最后board的结果还不一致；求和是对的，但是最后board记录的位置错了！。
            * */
            board[i][j]=1;
        }

        //本元素处理完后，递归处理周边元素
        recur1(board,m,n,k,i-1,j);
        recur1(board,m,n,k,i+1,j);
        recur1(board,m,n,k,i,j-1);
        recur1(board,m,n,k,i,j+1);
    }
    public static void recur2(int [][] board,int m,int n,int k,int i, int j){
        //下标不符合要求的，直接返回
        if(i<0||i>m-1||j<0||j>n-1){
            //System.out.println("下标不对");
            return;
        }
        //如果已经标记过，说明早被处理过的，直接返回
        if(board[i][j]==1||board[i][j]==-1){
            //System.out.println("被处理过");
            return;
        }

        if(i/10 + i%10 + j/10 + j%10>k){
            board[i][j]=-1;
            return ;
        }else{
            board[i][j]=1;
        }


        //本元素处理完后，递归处理周边元素
        recur2(board,m,n,k,i-1,j);
        recur2(board,m,n,k,i+1,j);
        recur2(board,m,n,k,i,j-1);
        recur2(board,m,n,k,i,j+1);
    }

}
