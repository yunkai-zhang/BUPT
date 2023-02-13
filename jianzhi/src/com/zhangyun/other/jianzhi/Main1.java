package com.zhangyun.other.jianzhi;

//冒泡排序链表
public class Main1 {
    public static void main(String[] args) {
        //伪头结点
        Node nFake=new Node(-1);
        Node n1=new Node(1);
        Node n2=new Node(3);
        Node n3=new Node(2);
        nFake.next=n1;
        n1.next=n2;
        n2.next=n3;

        int lLength=3;
        Node pre=nFake,left=n1,right=n2;
        //冒泡排序，不断把最值移动到一端
        for(int i=0;i<lLength-1;i++){
            //把left和right移动到对应位置
            int j=i;
            pre=nFake;
            left=n1;
            right=n2;
            while(j>0){
                pre=pre.next;
                left=left.next;
                right=right.next;
                j--;
            }

            while(right!=null){
                if(left.val>right.val){
                    //交换两个节点
                    Node temp=right.next;
                    pre.next=right;
                    right.next=left;
                    left.next=temp;

                    //向后移动指针
                    pre=pre.next;
                    left=pre.next;
                    right=left.next;
                }else{
                    //向后移动指针
                    pre=pre.next;
                    left=pre.next;
                    right=left.next;

                }
            }


        }
        //打印
        Node cur=nFake.next;
        while(cur!=null){
            System.out.println(cur.val);
            cur=cur.next;
        }
    }
}
class Node{
    int val;
    Node next;

    public Node(int val){
        this.val=val;
    }
}
