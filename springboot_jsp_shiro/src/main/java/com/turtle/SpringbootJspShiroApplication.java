package com.turtle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@EnableAsync(proxyTargetClass=true)
public class SpringbootJspShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJspShiroApplication.class, args);
    }

}
