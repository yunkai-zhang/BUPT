package com.zhangyun.redis02springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangyun.redis02springboot.pojo.User;
import com.zhangyun.redis02springboot.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SpringbootApplicationTests {

    //注入redisTemplate
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        /*redisTemplate操作不同的数据类型，api和redis指令是一样的：
        * opsForValue 操作字符串 类似String
        * opsForList 操作list 类似list
        * opsForSet
        * opsForHash
        * opsForZSet
        * opsForGeo
        * opsForHyperLogLog
        * */

        /*除了基本的操作，我们常用的方法都可以直接通过redisTemplate操作，比如事务，和基本的CRUD
        * */

        //获取redis的连接对象
        //RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        //connection.flushDb();

        /*redis数据库中键值对有乱码，是因为java自带序列化的原因，参考文章：https://blog.csdn.net/qq_16159433/article/details/121491555*/
        redisTemplate.opsForValue().set("key1","zhangyun1");
        System.out.println(redisTemplate.opsForValue().get("key1"));
    }

    @Test
    public void testJsonStringSerilization() throws JsonProcessingException {
        //真实的开发一般都使用json来传递对象,所以要把User对象序列化成（json格式的）String
        User user = new User("张云", 3);
        String userAsJsontypeString = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user1",userAsJsontypeString);//传送序列化成jsonstring后的对象
        System.out.println("user1=>"+redisTemplate.opsForValue().get("user1"));
    }
    @Test
    public void testObjectSerilization() throws JsonProcessingException {
        User user = new User("张云", 3);
        redisTemplate.opsForValue().set("user2",user);//直接传送对象
        System.out.println("user2=>"+redisTemplate.opsForValue().get("user2"));
    }

    @Autowired
    private RedisUtil redisUtil;
    @Test void testRedisUtil(){
        redisUtil.set("name","zhangyun");
        System.out.println(redisUtil.get("name"));
    }

}
