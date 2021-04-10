package com.whu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class PassportWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(PassportWebApplication.class,args);
    }
}
