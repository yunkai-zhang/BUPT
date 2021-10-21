import com.zhangyk.dao.UserMapper;
import com.zhangyk.pojo.User;
import com.zhangyk.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserMapperTest {
    @Test
    public void queryUserByIdTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.queryUserById(1);
        System.out.println(user);
        System.out.println("==========清理缓存===========");
        sqlSession.clearCache();
        System.out.println("===============================");
        User user2 = userMapper.queryUserById(2);
        System.out.println(user2);

        System.out.println(user == user2);

        sqlSession.commit();
        sqlSession.close();
    }

//    测试两个sqlsession共用一块二级缓存
    @Test
    public void queryUser2ndCacheTest(){

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.queryUserById(1);
        System.out.println(user);
        //一级缓存消亡的同时，缓存内容会被放进二级缓存
        sqlSession.close();

        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = userMapper2.queryUserById(1);
        System.out.println(user2);

        System.out.println(user == user2);

        sqlSession2.close();
    }



}
