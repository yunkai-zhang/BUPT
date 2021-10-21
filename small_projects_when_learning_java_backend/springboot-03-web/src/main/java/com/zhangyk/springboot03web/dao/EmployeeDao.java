package com.zhangyk.springboot03web.dao;

import com.zhangyk.springboot03web.pojo.Department;
import com.zhangyk.springboot03web.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//相当于component，把类给spring托管（注册为bean）
@Repository
//员工dao
public class EmployeeDao {

    //模拟数据库中的数据
    private static Map<Integer, Employee> employees=null;

    //员工有所属的部门。由于静态代码块中不能有非静态的，所以这个不能用在static代码块。
    @Autowired
    private DepartmentDao departmentDao;

    //初始化数据要在static(代码块)中加载,这样才能在系统已启动就首先加载数据
    static{
        //创建一个员工表
        employees = new HashMap<Integer, Employee>();

        //往员工表添加各个员工。alt+鼠标选中可以批量修改一个区域的代码
        employees.put(1001,new Employee(1001,"AA","A123@qq.com",1,new Department(101,"教学部")));
        employees.put(1002,new Employee(1002,"BB","B123@qq.com",0,new Department(102,"市场部")));
        employees.put(1003,new Employee(1003,"CC","C123@qq.com",1,new Department(103,"教研部")));
        employees.put(1004,new Employee(1004,"DD","D123@qq.com",0,new Department(104,"运营部")));
        employees.put(1005,new Employee(1005,"EE","E123@qq.com",1,new Department(105,"后勤部")));
    }

    //主键自增
    private static Integer initId=1006;
    //增加一个员工
    public void save(Employee employee){
        if(null==employee.getId()){
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));

        employees.put(employee.getId(),employee);
    }

    //查询全部员工的信息
    public Collection<Employee> getAll(){
        return employees.values();
    }

    //通过id查询员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    //通过id删除员工
    public void deleteEmployeeById(Integer id){
        employees.remove(id);
    }
}
