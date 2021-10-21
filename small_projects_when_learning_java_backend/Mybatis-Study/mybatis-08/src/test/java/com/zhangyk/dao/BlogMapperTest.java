package com.zhangyk.dao;

import com.zhangyk.pojo.Blog;
import com.zhangyk.utils.IDUtils;
import com.zhangyk.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BlogMapperTest {

    @Test
    public void addBlogTest(){

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        //设置一些基础信息并插入数据库
        Blog blog = new Blog();
        blog.setId(IDUtils.getId());
        blog.setTitle("Mybatis如此简单");
        blog.setAuthor("张云");
        blog.setCreateTime(new Date());
        blog.setViews(9999);

        mapper.addBlog(blog);

        //对blog修改一些信息，并插入数据库
        blog.setId(IDUtils.getId());
        blog.setTitle("java如此简单");
        mapper.addBlog(blog);

        blog.setId(IDUtils.getId());
        blog.setTitle("Spring如此简单");
        mapper.addBlog(blog);

        blog.setId(IDUtils.getId());
        blog.setTitle("微服务如此简单");
        mapper.addBlog(blog);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void queryBlogIfTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        HashMap map = new HashMap();

        //map.put("title","Spring如此简单");
        map.put("author","张云");


        List<Blog> blogs = blogMapper.queryBlogIf(map);

        for (Blog blog : blogs) {
            System.out.println(blog);
        }

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void queryBlogChooseTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        HashMap map = new HashMap();

        map.put("title","Spring如此简单");
        //map.put("author","张云");


        List<Blog> blogs = blogMapper.queryBlogChoose(map);

        for (Blog blog : blogs) {
            System.out.println(blog);
        }

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateBlogTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        HashMap map = new HashMap();

        map.put("title","Spring如此简单2");
        map.put("author","张云2");
        map.put("id","60fd4fa4fe6849b89a93601fb70c1978");

        blogMapper.updateBlog(map);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void queryBlogSqlBlockTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        HashMap map = new HashMap();

        //map.put("title","Spring如此简单");
        map.put("author","张云");


        List<Blog> blogs = blogMapper.queryBlogSqlBlock(map);

        for (Blog blog : blogs) {
            System.out.println(blog);
        }

        //查询不需要提交事务
        //sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void queryBlogForEachTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        HashMap map = new HashMap();
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("b0527a53125c42c3adb86484b7134b3e");
        strings.add("05493dee73a246c3b714c19e064f11b6");

        map.put("ids",strings);


        List<Blog> blogs = blogMapper.queryBlogForEach(map);

        for (Blog blog : blogs) {
            System.out.println(blog);
        }

        //查询不需要提交事务
        //sqlSession.commit();
        sqlSession.close();
    }
}
