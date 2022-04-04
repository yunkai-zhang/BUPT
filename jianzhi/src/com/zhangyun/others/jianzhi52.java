package com.zhangyun.others;

public class jianzhi52 {
    public static void main(String[] args) {
        ListNode a1=new ListNode(4);
        ListNode a2=new ListNode(1);
        ListNode c1=new ListNode(8);
        ListNode c2=new ListNode(4);

        ListNode b1=new ListNode(0);
        ListNode b2=new ListNode(1);

        a1.next=a2;
        a2.next=c1;
        c1.next=c2;
        b1.next=b2;
        b2.next=c1;

        System.out.println(getIntersectionNode(a1,b1).next.val);


    }
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /**
         链表判断环，判断相交，就考虑双指针
         */
        ListNode la=headA;
        ListNode lb=headB;

        //使用两个指针分别遍历两个链表，当发现指向同一节点时返回该节点；以节点的地址为判断节点相等的标准，因为节点的值可重复见（示例）
        while((la!=null)&&(lb!=null)){
            //System.out.println("la:"+la);
            //Syetem.out.println("lb:"+lb);
            if(la==lb){
                return la;
            }
            //while始终记住要移动指针
            la=la.next;
            lb=lb.next;
        }

        //经过遍历都没找到公共节点，说明无公共节点
        return null;
    }
}
 class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
      }
  }