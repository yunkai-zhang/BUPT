package com.zhangyun.other.paypal;


import java.util.*;

//通过所有测试案例
abstract class Employee{
    abstract void setSalary(int salary);
    abstract int getSalary();
    abstract void setGrade(String grade);
    abstract String getGrade();
    void label(){
        System.out.println("Employee's data:");
    }
}

class Engineer extends Employee{
    private int salary;
    private String grade;

    @Override
    void setSalary(int salary) {
        this.salary=salary;
    }

    @Override
    int getSalary() {
        return this.salary;
    }

    @Override
    void setGrade(String grade) {
        this.grade=grade;
    }

    @Override
    String getGrade() {
        return this.grade;
    }
}

class Manager extends Employee{
    private int salary;
    private String grade;

    @Override
    void setSalary(int salary) {
        this.salary=salary;
    }

    @Override
    int getSalary() {
        return this.salary;
    }

    @Override
    void setGrade(String grade) {
        this.grade=grade;
    }

    @Override
    String getGrade() {
        return this.grade;
    }
}

public class 贝宝Solution继承与实现 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sub = sc.nextLine();
        int n = Integer.parseInt(sub);
        for(int i=0;i<n;i++){
            String[] input = sc.nextLine().split(" ");
            if(input[0].equals("ENGINEER")){
                Engineer e = new Engineer();
                e.setSalary(Integer.parseInt(input[2]));
                e.setGrade(input[1]);
                e.label();
                System.out.println("GRADE : " + e.getGrade());
                System.out.println("SALARY : " + e.getSalary());
            }
            if(input[0].equals("MANAGER")){
                Manager e = new Manager();
                e.setSalary(Integer.parseInt(input[2]));
                e.setGrade(input[1]);
                e.label();
                System.out.println("GRADE : " + e.getGrade());
                System.out.println("SALARY : " + e.getSalary());
            }
        }
    }
}

