package com.zhangyk.springboot03web.controller;

import com.zhangyk.springboot03web.dao.DepartmentDao;
import com.zhangyk.springboot03web.dao.EmployeeDao;
import com.zhangyk.springboot03web.pojo.Department;
import com.zhangyk.springboot03web.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model){

        Collection<Employee> all = employeeDao.getAll();
        //给前端返回参数
        model.addAttribute("emps",all);

        return "emp/list";
    }

    //处理跳转到新增员工页面
    @GetMapping("/emp")
    public String toAddPage(Model model){

        //查出所有部门的信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);

        return "emp/add";
    }

    //处理新增员工的表单提交
    @PostMapping("/emp")
    //前端提交来的数据会被springmvc自动封装为employee对象
    public String addEmp(Employee employee){

        System.out.println("save=>"+employee);
        employeeDao.save(employee);

        /*重定向到/emps请求。
        * 增删改的时候尽量用重定向，不然容易找出表单重复提交
        * */
        return "redirect:/emps";
    }

    //去员工的修改页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp( @PathVariable("id")Integer id, Model model){
        //查出来的数据
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp",employee);

        //查出所有部门的信息供下拉框使用
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);

        return "emp/update";

    }

    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){

        employeeDao.save(employee);

        return "redirect:/emps";
    }

    //删除员工
    @GetMapping("/delEmp/{id}")
    public String deleteEmp(@PathVariable("id") int id){

        employeeDao.deleteEmployeeById(id);

        return "redirect:/emps";

    }
}
