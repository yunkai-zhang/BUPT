package com.zhangyk.boothelloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//启动类本身就是spring的一个组件（component）

/*程序的主入口
*
* @SpringBootApplication:标注这个类是一个springboot的应用
* */
@SpringBootApplication
public class BoothelloworldApplication {

    public static void main(String[] args) {
        //将springboot应用启动
        SpringApplication.run(BoothelloworldApplication.class, args);
    }

}
