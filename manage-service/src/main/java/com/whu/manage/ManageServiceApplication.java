package com.whu.manage;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.whu.manage.mapper")
@EnableDubbo
public class ManageServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageServiceApplication.class,args);
    }
}
