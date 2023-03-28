package com.zhangyun.other.paypal;

import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.joining;


//通过所有测试用例
class Result {

    /*
     * Complete the 'groupTransactions' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING_ARRAY transactions as parameter.
     */

    public static List<String> groupTransactions(List<String> transactions) {
        // Write your code here

        //construct strings
        HashMap<String,Integer> map=new HashMap();
        for(String transaction:transactions){
            map.put(transaction,map.getOrDefault(transaction,0)+1);
        }

        //get result Strings
        List<String> res=new ArrayList<>();
        for(Map.Entry<String,Integer> entry:map.entrySet()){
            res.add(entry.getKey()+" "+entry.getValue());
        }

        //use comparator to sort the list
        Collections.sort(res, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //first key then dic
                String[] kv1=o1.split(" ");
                String[] kv2=o2.split(" ");
                String key1=kv1[0];
                String key2=kv2[0];
                int val1=Integer.parseInt(kv1[1]);
                int val2=Integer.parseInt(kv2[1]);

                if(val1<val2){
                    return 1;
                }else if(val1>val2){
                    return -1;
                }else{
                    //use dic
                    return key1.compareTo(key2);
                }
            }
        });

        return res;

    }

}

public class 贝宝Solution根据订单商品名和订单数目排序 {
    public static void main(String[] args) throws IOException {

        List<String> transactions=new ArrayList<>();
        transactions.add("banana");
        transactions.add("pear");
        transactions.add("apple");

        List<String> result = Result.groupTransactions(transactions);
        System.out.println(result);

    }
}

