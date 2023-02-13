package com.zhangyun.other.stackQueue;

import java.util.Stack;

public class jianzhi09 {

    Stack stackIn=null;
    Stack stackOut=null;


    public jianzhi09() {
        stackIn=new Stack<>();
        stackOut=new Stack<>();
    }

    public void appendTail(int value) {
        //如果是传入integer类型，而非int的话，貌似会给integer再包裹一层，导致异常
        stackIn.push(value);
    }

    public int deleteHead() {
        //如果出栈不为空，直接pop一个就行。！！！包装类和基本数据类型的相互转换
        if(!stackOut.empty()){
            return (int) stackOut.pop();
        }
        //如果出栈为空，此时要删除节点,先就得把入栈的所有节点移动过来
        else{
            if(stackIn.empty()){
                //入栈也为空，说明整个逻辑队列为空，直接返回-1
                return -1;
            }else{
                //入栈不为空，把所有入栈元素转移到出栈；并弹出出栈的顶部。
                while(!stackIn.empty()){
                    stackOut.push(stackIn.pop());
                }
                return (int)stackOut.pop();
            }

        }

    }

    public static void main(String[] args) {

    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */