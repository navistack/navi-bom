package org.navistack.boot.autoconfigure.cache;

import org.navistack.framework.cache.KvCacheService;
import org.navistack.framework.cache.RedisKvCacheService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisOperations;

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class KvCacheAutoConfiguration {

    @Bean
    @ConditionalOnBean(RedisOperations.class)
    @ConditionalOnMissingBean(KvCacheService.class)
    public KvCacheService kvCacheService(RedisOperations<Object, Object> redisOperations) {
        return new RedisKvCacheService(redisOperations);
    }
}
