package com.ziyan.springbootredisson;

import com.ziyan.springbootredisson.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class SpringbootRedissonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedissonApplication.class, args);
    }
}
