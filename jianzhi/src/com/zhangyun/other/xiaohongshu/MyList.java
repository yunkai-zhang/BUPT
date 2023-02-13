//package com.zhangyun.other.xiaohongshu;
//
//public class MyList {
//    Node sudoHead;
//    Node sudoTail;
//    int size;
//
//    //初始化链表
//    public MyList(){
//        this.sudoHead=new Node(-1);
//        this.sudoTail=new Node(-1);
//        sudoHead.next=sudoTail;
//        sudoTail.prev=sudoHead;
//
//        size=0;
//    }
//
//    // 添加一个元素.head 为 true 添加到队首，false 添加到队尾
//    public void add(Integer data, boolean head){
//        //在头部添加节点
//        if(head){
//            Node cur=new Node(data);
//            cur.next=sudoHead.next;
//            sudoHead.next=cur;
//            cur.next.prev=cur;
//            cur.prev=sudoHead;
//
//            size++;
//        }else{//在尾部添加节点
//            Node cur=new Node(data);
//            cur.next=sudoTail;
//            cur.prev=sudoTail.prev;
//            cur.prev.next=cur;
//            sudoTail.prev=cur;
//
//            size++;
//        }
//    }
//
//    // 添加元素到指定位置之后
//    public void addAtIndex(Integer data, int index){//!!!!!反思：这里的index需要判断是不是在size范围内，因为index是用户输入的！
//        Node curPrev=sudoHead;
//        //移动指针到指定位置的前面
//        while(index-->0){
//            curPrev=curPrev.next;
//        }
//
//        //添加节点
//        Node cur=new Node(data);
//        cur.next=curPrev.next;
//        curPrev.next=cur;
//        cur.next.prev=cur;
//        cur.prev=curPrev;
//
//        size++;
//
//    }
//
//    // 删除元素，如果存在多个值一起删除，返回被删除的元素数量
//    public int delete(Integer data){
//        int sizeBeforeDelete=size;
//
//        Node curPrev=sudoHead;
//        while(curPrev.next!=sudoTail){
//            //删除节点。
//            if(curPrev.next.val==data){//！！！！！反思：这里的if应该改成while保证把连续的目标数全部删除
//                curPrev.next.next.prev=curPrev;
//                curPrev.next=curPrev.next.next;
//
//                size--;
//            }
//            curPrev=curPrev.next;
//
//        }
//    }
//
//    // 查找是否包含某个元素
//    public boolean find(Integer data){
//        return true;
//    }
//
//    // 获取列表长度
//    public int size(){
//        return this.size;
//    }
//}
//class Node {
//    int val;
//    Node prev;
//    Node next;
//
//    public Node(){};
//    public Node(int val){
//        this.val=val;
//    }
//}
