package com.zhangyun.springcloud.service;

import com.zhangyun.springcloud.mapper.DeptMapper;
import com.zhangyun.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//把服务层的实现类注入到spring，提取的时候是用接口提取，体现多态
@Service
public class DeptServiceImpl implements DeptService {
    //从spring容器获取dao层的接口bean，提供给服务层调用。即servie层调用dao层。
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public boolean addDept(Dept dept) {
        return deptMapper.addDept(dept);
    }

    @Override
    public Dept queryById(long id) {
        return deptMapper.queryById(id);
    }

    @Override
    public List<Dept> queryAll() {
        return deptMapper.queryAll();
    }
}
