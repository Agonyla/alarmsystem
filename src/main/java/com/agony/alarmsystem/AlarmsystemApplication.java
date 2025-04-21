package com.agony.alarmsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.agony.alarmsystem.mapper")
public class AlarmsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlarmsystemApplication.class, args);
    }

}
