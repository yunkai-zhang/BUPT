package com.zhangyun.other.alibaba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 阿里巴巴Main满二叉树个数 {
    static int res=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine().trim());

        //用列表保存树
        List<TreeNode> list=new ArrayList<>();
        //给可以存在树节点的位置都放上节点，只有真实在输入的树中出现的节点才会被连起来，也才能通过根节点list.get(0)做dfs访问到
        for (int i=0;i<n;i++){
            list.add(new TreeNode());
        }
        //根据每一行输入，连接列表中的节点
        for(int i=0;i<n;i++){
            String[] temp=br.readLine().trim().split(" ");
            int leftI=Integer.parseInt(temp[0]);
            int rightI=Integer.parseInt(temp[1]);

            if(leftI!=-1)list.get(i).left=list.get(leftI-1);
            if(rightI!=-1)list.get(i).right=list.get(rightI-1);
        }

        dfs(list.get(0));

        System.out.println(res);

        return;
    }

    /**
     * return：-1 表示本节点为根节点的树不是满二叉树。其他值 本节点为根节点的满二叉树的深度
     * */
    public static int dfs(TreeNode node){
        //触底
        if(node==null)return 0;//这里必须返回0而不是-1，这样叶子节点才能走进下面代码的else；而且空节点本身深度就是0，符合逻辑。
        //走到这，说明当前节点肯定不为空

        //后续遍历
        int left=dfs(node.left);
        int right=dfs(node.right);

        if(left==-1||right==-1||left!=right){
            return -1;
        }else{//走到这说明，左右子树都是满二叉树并且深度相等
            res++;//记数当前节点为根节点的满二叉树
            return left+1;//left和right子树深度相同，所以返回left的深度加一，表示node为根节点的满二叉树的深度。
        }
    }

}

//文件名对应的类才能定义为public
class TreeNode{
    TreeNode left;
    TreeNode right;
}
