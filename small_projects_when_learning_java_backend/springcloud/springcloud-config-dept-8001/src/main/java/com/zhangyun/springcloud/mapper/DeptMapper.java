package com.zhangyun.springcloud.mapper;

import com.zhangyun.springcloud.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
//功能等于bean，让接口被spring托管，表示当前接口是dao层的东西
@Repository
public interface DeptMapper {
    //根据阿里手册,接口中方法默认是public,所以最好不要再去写public修饰符
    public boolean addDept(Dept dept);

    public Dept queryById(long id);

    public List<Dept> queryAll();
}
