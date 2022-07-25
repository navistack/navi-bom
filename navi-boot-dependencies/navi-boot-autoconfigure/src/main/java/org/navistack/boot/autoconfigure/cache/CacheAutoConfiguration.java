package org.navistack.boot.autoconfigure.cache;

import org.navistack.framework.cache.CacheService;
import org.navistack.framework.cache.RedisCacheService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisOperations;

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class CacheAutoConfiguration {

    @Bean
    @ConditionalOnBean(RedisOperations.class)
    @ConditionalOnMissingBean(CacheService.class)
    public CacheService cacheService(RedisOperations<Object, Object> redisOperations) {
        return new RedisCacheService(redisOperations);
    }
}
