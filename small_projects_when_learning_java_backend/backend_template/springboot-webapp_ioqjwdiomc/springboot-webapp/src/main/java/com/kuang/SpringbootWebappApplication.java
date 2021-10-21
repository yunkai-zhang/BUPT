package com.kuang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.kuang"})
public class SpringbootWebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebappApplication.class, args);
    }

}
