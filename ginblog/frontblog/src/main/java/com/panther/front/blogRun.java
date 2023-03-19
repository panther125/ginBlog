package com.panther.front;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Gin 琴酒
 * @data 2023/3/13 23:12
 */
@SpringBootApplication(scanBasePackages = {"com.panther"})
@MapperScan("com.panther.mapper")
@EnableScheduling
public class blogRun {
    public static void main(String[] args) {
        SpringApplication.run(blogRun.class,args);
    }
}
