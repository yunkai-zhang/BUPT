package com.zhang;

import redis.clients.jedis.Jedis;

public class TestPing {
    public static void main(String[] args) {
        //1. new Jedis对象
        Jedis jedis =new Jedis("120.53.244.17",6379);
        //2. jedis所有的命令就是我们之前学的所有redis指令,所以redis指令学习很重要
        System.out.println(jedis.ping());
        //3. 关闭连接。养成开启连接用完后随手关闭的习惯。
        jedis.close();
    }

}
