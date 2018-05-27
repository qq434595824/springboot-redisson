package com.ziyan.springbootredisson.config;

import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ziyan
 * @email 434595824@qq.com
 * @date 2018/5/26
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    private Config redissonConfig;

    public Config getRedissonConfig() {
        return redissonConfig;
    }

    public void setRedissonConfig(Config redissonConfig) {
        this.redissonConfig = redissonConfig;
    }
}