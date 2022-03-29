package com.zhangyun.others;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class jianzhi32 {

    public static void main(String[] args) {
        TreeNode t5 = new TreeNode(7);
        TreeNode t4 = new TreeNode(15);
        TreeNode t3 = new TreeNode(10);
        TreeNode t2 = new TreeNode(9);
        TreeNode t1 = new TreeNode(3);
        t3.right=t5;
        t3.left=t4;
        t1.right=t3;
        t1.left=t2;

        System.out.println(levelOrder(t1));

    }
    public static List<List<Integer>> levelOrder(TreeNode root) {
        /*
        总是使用deque不会错，它也代替了Stack：https://blog.csdn.net/devnn/article/details/82716447
         */

        //创建双端队列;默认节点last进first出;注意用LinkedList实现Deque！！！
        Deque <TreeNode> deque=new LinkedList<>();
        Deque <TreeNode> deque2=new LinkedList<>();
        if(root!=null) deque.offerLast(root);

        List<List<Integer>> resultList=new ArrayList<>();

        //决定是否从左(first)往右（last）打印节点;第一行从左往右，所以初始true
        boolean left2right=true;

        while(!deque.isEmpty()){
            List<Integer> tempList=new ArrayList();
            //不能在for中判断size，因为加了子节点size就变了
            int dequeSize=deque.size();
            for(int i=0;i<dequeSize;i++){
                //从左往右打印节点，就先放左子节点进队列(默认都从尾部入)
                if(left2right){
                    TreeNode temp=deque.pollFirst();
                    tempList.add(temp.val);
                    //!!!千万不要把null存入队列，它会占用队列的一个位置，且null.val null.left都会导致错误
                    if(temp.left!=null)deque2.offerLast(temp.left);
                    if(temp.right!=null)deque2.offerLast(temp.right);

                }else{//从右往左打印节点，就先放右子节点进队列(默认都从尾部入)
                    TreeNode temp=deque.pollLast();
                    tempList.add(temp.val);
                    if(temp.right!=null)deque2.offerLast(temp.right);
                    if(temp.left!=null)deque2.offerLast(temp.left);

                }
            }
            //更改走向标志，换一个方向的子节点先入队列
            left2right=!left2right;
            resultList.add(tempList);

            Deque <TreeNode> tempDeque=deque;
            deque=deque2;
            deque2=tempDeque;
        }

        return resultList;
    }

}
class TreeNode {
     int val;
    TreeNode left;
     TreeNode right;
 TreeNode(int x) { val = x; }
 }
