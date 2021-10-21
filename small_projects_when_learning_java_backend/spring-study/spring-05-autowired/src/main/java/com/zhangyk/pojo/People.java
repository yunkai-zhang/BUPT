package com.zhangyk.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class People {

    //如果显式定义了autowired的required属性为false，说明这个对象可以为null，否则不允许为空。一般用不到，能看懂即可。
    @Autowired(required = false)
    private Cat cat;
    @Autowired
    //通过类型和名字都无法匹配的时候，可以用@Qualifier自己指定要匹配哪个对象
    @Qualifier(value="dog2")
    private Dog dog;
    private String name;

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
}
