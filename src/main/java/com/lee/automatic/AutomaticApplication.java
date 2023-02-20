package com.lee.automatic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动主类
 *
 * @author lee
 */
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class AutomaticApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomaticApplication.class, args);
    }

}
