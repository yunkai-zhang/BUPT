package com.zhangyun.other.tencent;
/**
 * AC了
 * */
public class Main链表分组交换 {
    public static void main(String[] args) {
        ListNode one=new ListNode(1);
        ListNode one1=new ListNode(1);
        ListNode two=new ListNode(2);
        ListNode two1=new ListNode(2);
        ListNode three=new ListNode(3);
        ListNode three1=new ListNode(3);
        ListNode four=new ListNode(4);
        //ListNode five=new ListNode(5);
        one.next=one1;
        one1.next=two;
        two.next=two1;
        two1.next=three;
        three.next=three1;
        three1.next=four;
        //four.next=five;

        ListNode res=reorderList(one);

        System.out.println(res.val);
    }

    public static ListNode reorderList (ListNode head) {
        // write code here
        ListNode dummyNode=new ListNode(-1);
        dummyNode.next=head;
        ListNode prev=dummyNode,next,cut;

        //至少有三个可用节点才会尝试分割
        while(prev.next!=null&&prev.next.next!=null&&prev.next.next.next!=null){
            //找到cut点
            cut=prev.next.next.next;

            //找到当前两组的下一个节点
            boolean second2=true;
            if(cut.next!=null){
                next=cut.next.next;
                second2=true;
            } else {
                next=cut.next;
                second2=false;
            }

            //交换两组的位置
            prev.next.next.next=next;
            if(second2)cut.next.next=prev.next;
            else cut.next=prev.next;
            prev.next=cut;

            //移动prev
            if(second2)prev=cut.next.next.next;
            else prev=cut.next.next;
        }

        return dummyNode.next;

    }

    static class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val){
            this.val=val;
        }
    }
}

