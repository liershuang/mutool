package com.mutool.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(
        exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = {"com.mutool.job"}
)
public class MutoolJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(MutoolJobApplication.class, args);
    }

}
