package com.zhangyun.other.jianzhi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ACMIO {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int n=Integer.parseInt(br.readLine());
        String[] strs=br.readLine().trim().split(" ");

        //我：idea提示了lamda可以省略，因为sort默认升序。但是字符串排序毕竟不直观，我还是显示用lamda排序指明排序方法。
        //我：s1.compareTo(s2)的值相当于s1-s2，即比较s1和s2的字典大小
        Arrays.sort(strs,(o1, o2)->(o1.compareTo(o2)));

        StringBuilder sb=new StringBuilder();
        for(String str:strs){
            sb.append(str+" ");
        }

        String res=sb.toString().trim();

        System.out.println(res);
    }
}
