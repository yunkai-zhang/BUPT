package com.zhangyk.reflection;

import java.lang.annotation.*;
import java.lang.reflect.Field;

//练习反射操作注解
public class Test12 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class c1 = Class.forName("com.zhangyk.reflection.Student2");

        //通过反射获得注解
        System.out.println("============通过反射获得注解=============");
        Annotation[] annotations = c1.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        //获得指定"类注解"（类注解即针对类用的注解）的value值
        System.out.println("============获得指定\"类注解\"的value值=============");
        TableZhangyk tableZhangyk = (TableZhangyk) c1.getAnnotation(TableZhangyk.class);
        String value =  tableZhangyk.value();
        System.out.println(value);

        //获得类中指定的注解(比如方法注解，属性注解等)
        //这里是尝试获得：Student2类中，name属性拥有的FieldZhangyk注解中所填的value是什么
        System.out.println("============获得类中指定的注解(比如方法注解，属性注解等)的value值=============");
        //获取Field类（字段类）
        Field name = c1.getDeclaredField("name");
        //借助字段类，获取该字段的某指定注解：FieldZhangyk注解
        FieldZhangyk fieldZhangyk = name.getAnnotation(FieldZhangyk.class);
        //打印获得的字段注解中，存入的各个value
        System.out.println(fieldZhangyk.columnName());
        System.out.println(fieldZhangyk.type());
        System.out.println(fieldZhangyk.length());
    }
}

@TableZhangyk("db_student")
class Student2{
    @FieldZhangyk(columnName = "db_id",type="int",length = 10)
    private int id;
    @FieldZhangyk(columnName = "db_age",type="int",length = 3)
    private int age;
    @FieldZhangyk(columnName = "db_name",type="varchar",length = 10)
    private String name;

    public Student2() {
    }

    public Student2(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

//类的注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TableZhangyk{
    String value();
}

//属性的注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldZhangyk{
    String columnName();
    String type();
    int length();
}