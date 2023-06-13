package com.zhangyun.other.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 网络崩溃后逐渐恢复，给定节点数N和节点间通信日志log[[节点1，节点2，通信时间],[节点1，节点2，通信时间],[节点1，节点2，通信时间],..]。
 * 问最早什么时候整个网络是联通的。有union set，dfs等方式求解图的联通问题
 *
 * */

public class Main网络何时联通 {
    public static void main(String[] args) {
        int[][] log = new int[4][3];
        log[0]=new int[]{0, 1, 1};
        log[1]=new int[]{2, 3, 5};
        log[2]=new int[]{1, 3, 6};
        log[3]=new int[]{0, 2, 8};

        Arrays.sort(log,((o1, o2) -> (o1[2]-o2[2])));

        List<List<Integer>> clusters=new ArrayList<>();

        for(int i=0;i<log.length;i++){

        }
    }
}
