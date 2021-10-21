package com.example.springboot10redis;

import com.example.springboot10redis.pojo.User;
import com.example.springboot10redis.utils.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Springboot10RedisApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {

        /*
        * redisTemplate:操作不同的数据类型，api和我们的指令是一样的。
        * redisTemplate.opsForValue()：操作字符串，类似String
        * redisTemplate.opsForList：操作List，类似List
        * redisTemplate.opsForSet
        * redisTemplate.opsForHash
        * redisTemplate.opsForZSet
        * redisTemplate.opsForGeo
        * redisTemplate.opsForHyperLogLog
        * 除了基本的操作，我们常用的方法都可以直接通过redisTemplate操作，比如事务，比如基本的CRUD
        *
        * 获取redis的连接对象
        * RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        * connection.flushDb();
        * connection.flushAll();
        *
        * */
        redisTemplate.opsForValue().set("myKey","张云学java");
        System.out.println(redisTemplate.opsForValue().get("myKey"));

    }

    @Test
    public void test() throws JsonProcessingException {
        //真是的开发一般都使用json来传递对象
        User user = new User("张云学", 3);
        //把user序列化。springboot中有objectmapper，objectmapper是jackson对象。objectmapper可以把user编程json对象
        //String jsonUser = new ObjectMapper().writeValueAsString(user);

        redisTemplate.opsForValue().set("user",user);
        Object user1 = redisTemplate.opsForValue().get("user");
        System.out.println(user1);
    }

    @Test
    public void test1(){
        redisUtil.set("name","zhangyun");
        System.out.println(redisUtil.get("name"));
    }

}
