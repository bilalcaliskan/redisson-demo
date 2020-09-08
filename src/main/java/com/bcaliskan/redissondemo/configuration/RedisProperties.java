package com.bcaliskan.redissondemo.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "redisson-demo.redis")
public class RedisProperties {

    private String master;
    private List<String> nodes;
    private String keyPrefix;
    private long fixedDelayMillis;
    private int keyExpireMinutes;

}