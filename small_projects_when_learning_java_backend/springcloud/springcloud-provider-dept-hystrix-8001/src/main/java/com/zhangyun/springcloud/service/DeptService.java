package com.zhangyun.springcloud.service;

import com.zhangyun.springcloud.pojo.Dept;

import java.util.List;

//service层接口的函数和dao（mapper）层长得一样
public interface DeptService {
    public boolean addDept(Dept dept);

    public Dept queryById(long id);

    public List<Dept> queryAll();
}
