package com.wly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wly.repository")
public class LandApplication {
    public static void main(String[] args) {
        SpringApplication.run(LandApplication.class,args);
    }

}
