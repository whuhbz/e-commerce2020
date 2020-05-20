package com.whu.manage;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
manageWeb服务(controller层)不需使用数据库，
pom.xml中不需添加jdbc，mysql等依赖，
springboot启动时会默认配置datasource,
所以添加exclude取消该配置，否则无法启动
**/
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
@EnableDubbo
public class ManageWebApplicationTests {
    public static void main(String[] args) {
        SpringApplication.run(ManageWebApplicationTests.class,args);
    }
}
