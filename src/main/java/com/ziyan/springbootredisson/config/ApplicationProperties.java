package com.ziyan.springbootredisson.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author ziyan
 * @email 434595824@qq.com
 * @date 2018/5/26
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    private Map<String,Object> redissonConfig;

    public Map<String, Object> getRedissonConfig() {
        return redissonConfig;
    }

    public void setRedissonConfig(Map<String, Object> redissonConfig) {
        this.redissonConfig = redissonConfig;
    }
}