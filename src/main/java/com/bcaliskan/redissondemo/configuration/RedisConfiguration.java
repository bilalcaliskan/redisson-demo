package com.bcaliskan.redissondemo.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
@Configuration
public class RedisConfiguration {
    
    @Bean
    public Config config() {
        return new Config();
    }

    @Bean
    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redisson) {
        return new RedissonConnectionFactory(redisson);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedissonConnectionFactory redissonConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redissonConnectionFactory);
        return template;
    }

    @Bean
    public RedissonClient redissonClient(RedisProperties redisProperties, Config config) {
        config.useSentinelServers()
                .setMasterName(redisProperties.getMaster())
                .setDatabase(2);

        redisProperties.getNodes()
                .forEach(node -> {
                    log.info("Adding {} as sentinel server", node);
                    config.useSentinelServers()
                            .addSentinelAddress(node);
                });

        return Redisson.create(config);
    }



}
