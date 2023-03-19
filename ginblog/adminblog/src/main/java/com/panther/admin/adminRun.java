package com.panther.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Gin 琴酒
 * @data 2023/3/16 17:06
 */
@SpringBootApplication(scanBasePackages = {"com.panther"})
@MapperScan("com.panther.mapper")
public class adminRun {
    public static void main(String[] args) {
        SpringApplication.run(adminRun.class,args);
    }
}
