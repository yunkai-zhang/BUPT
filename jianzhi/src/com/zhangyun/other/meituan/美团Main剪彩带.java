package com.zhangyun.other.meituan;
//失败的解法
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 美团Main剪彩带 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine=br.readLine().trim().split(" ");
        int N=Integer.parseInt(firstLine[0]);
        int K=Integer.parseInt(firstLine[1]);
        String[] secondLine=br.readLine().trim().split(" ");

        int[] dp=new int[secondLine.length];
        dp[0]=1;
        Map<Integer,Integer> map=new HashMap<>();
        map.put(Integer.parseInt(secondLine[0]),0);//map要记录最早出现，而不是最近出现
        Set<Integer> prevSet=new HashSet<>();
        prevSet.add(Integer.parseInt(secondLine[0]));
        int res=1;

        //从inedx1 开始处理
        for(int i=1;i<secondLine.length;i++){
            Integer temp=Integer.parseInt(secondLine[i]);
            int prevIndex=map.getOrDefault(temp,-1);
            if(i-prevIndex>dp[i-1]){//新出现一个字符，要做特殊判断
                if(prevSet.size()==K){
                    dp[i]=1;//不能加入dpi-1，另起炉灶
                    prevSet=new HashSet<>();
                    prevSet.add(temp);
                    map=new HashMap<>();
                    map.put(temp,i);
                }else{
                    dp[i]=dp[i-1]+1;
                    prevSet.add(temp);
                    map.put(temp,i);
                }
            }else{
                dp[i]=dp[i-1]+1;
            }
            //不管怎么处理，都要更新temp的index
            //map.put(temp,i);

            //更新历史最大
            res=res>dp[i]?res:dp[i];
        }

        System.out.println(res);
        return;

    }
}
