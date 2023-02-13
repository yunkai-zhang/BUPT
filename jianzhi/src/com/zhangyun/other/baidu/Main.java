package com.zhangyun.other.baidu;

public class Main {

    public static void main(String[] args){

        Node one=new Node(1);
        Node two=new Node(2);
        Node three=new Node(3);
        one.next=two;
        two.next=three;

        Node reversedLinkedListHead=reverse(one);
        Node cur=reversedLinkedListHead;
        while(cur!=null){
            System.out.println(cur.val);
            cur=cur.next;
        }

    }

    public static Node reverse(Node head){

        if(head==null) return null;

        Node pre=null,cur=head;
        while(cur!=null){
            Node next=cur.next;

            cur.next=pre;

            pre=cur;
            cur=next;
        }

        return pre;

    }


}

class Node{
    int val;
    Node next;

    public Node(){};
    public Node(int val){
        this.val=val;
    };
}
