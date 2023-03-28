package com.zhangyun.other.tencent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * AC了
 * */

public class Main重组字符串 {
    static int res=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N= Integer.parseInt(br.readLine().trim());
        ArrayList<String> list=new ArrayList<>();
        for(int i=0;i<N;i++){
            list.add(br.readLine().trim());
        }

        int[] used=new int[26];
        recur(used,list,0);

        System.out.println(res);
    }

    public static void recur(int[]used,ArrayList<String> list,int strUsedNum){
        if(strUsedNum==list.size()){
            res++;//不需要记录生成的字符串，所以只需要计数，不需要SB
            return;
        }

        //针对第strUsedNum条字符串拿一个元素
        String curStr=list.get(strUsedNum);

        //本层用过的字符，不能在本层再次被使用。否则会针对该字符重复计数。
        HashSet<Character>set=new HashSet<>();

        for(int i=0;i<curStr.length();i++){
            char ch=curStr.charAt(i);
            if(used[ch-'a']==1)continue;
            if(set.contains(ch))continue;
            else {
                //使用当前字符

                //使用当前字符
                used[ch-'a']++;
                set.add(ch);

                recur(used,list,strUsedNum+1);

                //回溯
                used[ch-'a']--;

            }
        }
    }
}
