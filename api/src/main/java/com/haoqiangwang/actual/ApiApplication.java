package com.haoqiangwang.actual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
* @Description: springboot项目的启动类
 * 实现SpringBootServletInitializer 是为了部署在tomcat容器内
* @Author:      haoqiangwang3
* @CreateDate:  2020/7/27
*/
@SpringBootApplication
@ComponentScan({"com.haoqiangwang"})
public class ApiApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApiApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}