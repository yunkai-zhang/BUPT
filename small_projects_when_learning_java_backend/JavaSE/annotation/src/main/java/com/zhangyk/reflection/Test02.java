package com.zhangyk.reflection;

//什么叫反射
public class Test02 {

    public static void main(String[] args) throws ClassNotFoundException {
        //通过反射获取类的Class对象
        Class c1 = Class.forName("com.zhangyk.reflection.User");
        System.out.println(c1);

        Class c2 = Class.forName("com.zhangyk.reflection.User");
        Class c3 = Class.forName("com.zhangyk.reflection.User");
        Class c4 = Class.forName("com.zhangyk.reflection.User");
        /*
        1. 通过hashcode判断不同引用是不是指向一个对象：发现hashcode相等-》一个类在内存中只有一个Class对象
        2. 一个类被加载后，类的整个结构都会被封装在Class对象中

        这里最好用 == 判断两个对象是否相等，因为hashcode不同，类也可能相同（很小概率）
        */
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());
        System.out.println(c4.hashCode());

    }
}

//实体类:pojo
class User{
    private String name;
    private int id;
    private int age;

    public User() {
    }

    public User(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }
}
