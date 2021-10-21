package com.kuang.controller;

import com.kuang.mapper.DepartmentMapper;
import com.kuang.mapper.EmployeeMapper;
import com.kuang.pojo.Department;
import com.kuang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    //查询所有员工，返回列表页面
    @GetMapping("/emps")
    public String list(Model model){
        List<Employee> employees = employeeMapper.getEmployees();
        //将结果放在请求中
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    //to员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //查出所有的部门，提供选择
        List<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    //员工添加功能
    //接收前端传递的参数，自动封装成为对象[要求前端传递的参数名，和属性名一致]
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println(employee);
        employeeMapper.save(employee); //保存员工信息
        //回到员工列表页面，可以使用redirect或者forward
        return "redirect:/emps";
    }

    //to员工修改页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id,Model model){
        //根据id查出来员工
        Employee employee = employeeMapper.get(id);
        System.out.println(employee);
        //将员工信息返回页面
        model.addAttribute("emp",employee);
        //查出所有的部门，提供修改选择
        List<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("departments",departments);

        return "emp/update";
    }


    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
        employeeMapper.update(employee);
        //回到员工列表页面
        return "redirect:/emps";
    }


    @GetMapping("/delEmp/{id}")
    public String delEmp(@PathVariable("id") Integer id){
        employeeMapper.delete(id);
        return "redirect:/emps";
    }

}
