package com.zhangyun.demo10stream;

import java.util.Arrays;
import java.util.List;

/**
 * Description：
 * 题目要求： 用一行代码实现！
 * 现在有5个用户，筛选：
 * 1. Id 必须是偶数
 * 2.年龄必须大于23
 * 3. 用户名转为大写
 * 4. 用户名字母倒着排序
 * 5. 只能输出一个用户
 *
 **/

public class TestStream {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 23);
        User u2 = new User(2, "b", 23);
        User u3 = new User(3, "c", 23);
        User u4 = new User(6, "d", 24);
        User u5 = new User(4, "e", 25);

        //集合就是存储
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        //计算交给Stream流
        // 把list转换成流
        list.stream()
                .filter(user -> {return user.getId()%2 == 0;})//把对象流中id是偶数的对象筛选出来，流向下游
                .filter(user -> {return user.getAge() > 23;})//把对象流中age>23的对象筛选出来，流向下游
                .map(user -> {return user.getName().toUpperCase();})//把对象流中所有对象的名字取出来，变为大写，把名字流向下游（注意这异步后没有对象流，只有字符串流了）
                .sorted((user1, user2) -> {return user2.compareTo(user1);})//把字符串流排序
                .limit(1)//只从流中输出一个字符串
                .forEach(System.out::println);
    }
}
