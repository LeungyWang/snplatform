package com.wly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients
@ServletComponentScan
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class,args);

    }

    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }
}
