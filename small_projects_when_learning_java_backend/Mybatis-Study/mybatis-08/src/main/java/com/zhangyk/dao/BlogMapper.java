package com.zhangyk.dao;

import com.zhangyk.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {

    //插入数据
    int addBlog(Blog blog);

    //查询博客
    List<Blog> queryBlogIf(Map map);

    //使用choosewhen
    List<Blog> queryBlogChoose(Map map);

    //更新博客
    int updateBlog(Map map);

    //查询博客
    List<Blog> queryBlogSqlBlock(Map map);

    //使用foreach
    List<Blog> queryBlogForEach(Map map);
}
