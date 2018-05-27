package com.ziyan.springbootredisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ziyan
 * @email 434595824@qq.com
 * @date 2018/5/26
 */
@Configuration
@ConditionalOnProperty(value = "application.redissonConfig.enabled", matchIfMissing = true)
public class RedissonConfiguration {


    @Bean
    public RedissonClient redissonClient(ApplicationProperties applicationProperties){
        RedissonClient redisson = Redisson.create(applicationProperties.getRedissonConfig());
        return redisson;
    }
}