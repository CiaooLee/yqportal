package com.yaqiu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.yaqiu.mapper")
public class YqportalApplication {

    public static void main(String[] args) {
        SpringApplication.run(YqportalApplication.class, args);
    }

}
