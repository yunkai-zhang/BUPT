package com.zhang;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTX {
    public static void main(String[] args) {
        //初始化
        Jedis jedis =new Jedis("120.53.244.17",6379);
        jedis.flushDB();

        //把得到json格式的字符串，作为kv的v
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","world");
        jsonObject.put("name","zhangyun");
        String result = jsonObject.toJSONString();
        System.out.println("result -> " + result);

        //开启事务
        Transaction transaction = jedis.multi();

        //！！！可能执行失败并且要回滚的代码块，使用trycatch包裹起来！
        try {
            //命令入队
            transaction.set("user1",result);
            /*
            * 和redis环境不同，这里是javatrycatch环境，虽然1/0是运行时异常，但出现异常会走catch中的取消事务，exec没执行就抛错了，所以try中的set都不会执行
            *
            * exec都没执行，跟redis的事务执行异常根本没关系
            * */
            int i=1/0;//
            transaction.set("user2",result);

            //事务执行
            transaction.exec();
        } catch (Exception e) {
            //发生异常时放弃事务（事务回滚）
            transaction.discard();
            e.printStackTrace();
        } finally {
            //无论事务成功与否都会来到finally代码块。如果能看到user1和user2的值，则说明事务执行成功；如果值为空则事务失败。
            System.out.println("user1 -> " +jedis.get("user1"));
            System.out.println("user2 -> " +jedis.get("user2"));
            //关闭连接
            jedis.close();
        }
    }
}
