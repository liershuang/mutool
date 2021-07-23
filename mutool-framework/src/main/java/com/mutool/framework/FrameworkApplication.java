package com.mutool.framework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@MapperScan("com.mutool.**.mapper")
@SpringBootApplication(scanBasePackages = {"com.mutool"})
public class FrameworkApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(FrameworkApplication.class, args);
    }


}
