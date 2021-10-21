package com.zhangyk.mapper;

import com.zhangyk.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

//之前像sqlSession.getMapper这种操作都是在Test类中做的，现在用Spring-Mybatis后我们把这些内容放到MapperImpl类中。
public class UserMapperImpl implements UserMapper {

    //我们所有操作，原来都使用sqlSession来执行；现在都使用使用SqlSessionTemplate
    private SqlSessionTemplate sqlSession;

    //设置sqlSession的set函数,是必须的。因为Spring-mybatis配置文件中给bean设置property时，内部用到的就是class的set方法。
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<User> selectUser() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
