package com.ziyan.springbootredisson.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author ziyan
 * @email 434595824@qq.com
 * @date 2018/5/26
 */
@Configuration
@ConditionalOnProperty(value = "application.redissonConfig.enabled", matchIfMissing = true)
public class RedissonConfiguration {

    final static Pattern CLASS_PATTERN = Pattern.compile("[a-zA-Z]+[0-9a-zA-Z_]*(\\.[a-zA-Z]+[0-9a-zA-Z_]*)*\\.[a-zA-Z]+[0-9a-zA-Z_]*");

    @Bean
    public RedissonClient redissonClient(ApplicationProperties applicationProperties) throws IOException {
        Map<String, Object> redissonConfig = applicationProperties.getRedissonConfig();
        final Queue<Map<String, Object>> temp = new LinkedList<>(Arrays.asList(redissonConfig));
        // BFS
        while (!temp.isEmpty()) {
            Map<String, Object> map = temp.poll();
            map.forEach((key, value) -> {
                if (value instanceof LinkedHashMap) {
                    List<String> keys = (List<String>) ((Map) value).keySet().stream().collect(Collectors.toList());
                    keys.sort(String::compareTo);
                    // 校验是否数组,key以1自增长的map
                    boolean arrays = true;
                    for (int i = 0; i < keys.size(); i++) {
                        if (!StringUtils.equals(keys.get(i), String.valueOf(i))) {
                            arrays = false;
                            break;
                        }
                    }
                    if (arrays) {
                        map.replace(key, ((LinkedHashMap) value).values());
                        return;
                    } else {
                        temp.add((LinkedHashMap) value);
                    }
                } else if (value instanceof String) {
                    // 类名正则
                    if (CLASS_PATTERN.matcher((String) value).matches()) {
                        try {
                            Class.forName((String) value);
                            Map<String, String> m = new HashMap<>(1);
                            m.put("class", (String) value);
                            map.replace(key, m);
                        } catch (ClassNotFoundException e) {
                            // 无法通过反射拿到对象，不做处理
                            e.printStackTrace();
                        }
                    } else if (StringUtils.isBlank((String) value)) {
                        // 空字符串处理
                        map.replace(key, null);
                    }
                }
            });
        }

        Config config = Config.fromJSON(new ObjectMapper().writeValueAsString(redissonConfig));
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}