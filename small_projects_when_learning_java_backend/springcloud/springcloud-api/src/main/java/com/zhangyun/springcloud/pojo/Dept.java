package com.zhangyun.springcloud.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/*
* Dept实体类
*
* ORM对象关系映射（类表关系映射）；即mysql表对应真实的pojo类。
*
* 所有的实体类务必实现序列化，否则传输可能报错
* */
@Data
//生成无参构造
@NoArgsConstructor
/*
* 开启支持链式写法
* 没开启链式写法时：dept.setDeptNo(1);dept.setDName("name");dept.setDb_source("DB01");
* 开启链式写法后：dept.setDeptNo(1).setDName("name").setDb_source("DB01");
* 链式写法虽然方便，但是也让排错困难了些。
* */
@Accessors(chain = true)
public class Dept implements Serializable {
    private Long deptno;
    private String dname;

    //这个字段用于看当前表存在于哪个数据库中。因为微服务架构中，一个服务对应一个数据库；同一个信息可能存在不同的数据库。
    private String db_source;

    /*虽然导入了lombok，但这里自定义一个有参构造。只需要dname字段，因为deptno自增自己生成，db_source由数据库函数自动生成。
    * 不用lombok的原因猜测是：可能lombok的有参构造注解只支持全参数输入的构造函数
    * */
    public Dept(String dname){
        this.dname=dname;
    }

}
