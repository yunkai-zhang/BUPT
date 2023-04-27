package com.zhangyun.other.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    static int ans=Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Node [] nodes=new Node[n];

        for(int i=0;i<n;i++){
            nodes[i]=new Node();
            nodes[i].id=i;
        }

        for(int i=0;i<n;i++){
            int id= sc.nextInt();
            int parent_id=sc.nextInt();
            int val=sc.nextInt();
            nodes[id].val=val;
            nodes[id].max_gain=val;
            if(parent_id!=-1&&parent_id!=id){
                nodes[parent_id].nexts.add(nodes[id]);
            }
        }

        for(int i=0;i<n;i++){
            if(nodes[i].visited==false){
                dfs(nodes[i]);
            }
        }
        System.out.println(ans);
    }

    public static void dfs(Node node){
        int temp_gain=node.max_gain;
        for(int i=0;i<node.nexts.size();i++){
            Node nxt_node=node.nexts.get(i);
            if(nxt_node.visited==false){
                dfs(nxt_node);
            }

            node.max_gain=Math.max(node.max_gain,temp_gain+nxt_node.max_gain);

        }
        node.visited=true;
        ans=Math.max(ans, node.max_gain);

    }
}
class Node{
    int id;
    List<Node> nexts=new ArrayList<>();
    int val;
    int max_gain;
    boolean visited;
}
