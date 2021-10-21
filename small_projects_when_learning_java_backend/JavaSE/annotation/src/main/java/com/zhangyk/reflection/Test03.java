package com.zhangyk.reflection;

//测试Class类的创建方式有哪些
public class Test03 {

    public static void main(String[] args) throws ClassNotFoundException {
        Person person=new Student();
        System.out.println("这个人是："+ person.name);

        //方式一：通过对象获得
        //forName最实用，有对象了用getClass意义不大
        Class c1 = person.getClass();

        //方式二：使用静态方法forname获得
        //除了forName，其他的方法基本不用
        Class c2 = Class.forName("com.zhangyk.reflection.Student");

        //方式3：通过类名.class获得
        Class c3=Student.class;

        //一个类的Class对象在jvm中只有一个，所以打印为true
        System.out.println(c1==c2&&c2==c3);

        //方式4：基本内置类型的包装类都有一个Type属性
        Class c4 = Integer.TYPE;
        //打印结果是int，因为是基本数据类型；相比较来说，c123都是Student类的Class对象
        System.out.println(c4);

        //获取父类类型
        Class c5 = c1.getSuperclass();
        System.out.println(c5);
    }

}

class Person{
    String name;

    public Person(){

    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Student extends Person{
    public Student(){
        this.name="学生";
    }
}

class Teacher extends Person{
    public Teacher(){
        this.name="老师";
    }
}