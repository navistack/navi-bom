package org.navistack.boot.autoconfigure.cache;

import org.navistack.framework.cache.CacheService;
import org.navistack.framework.cache.DefaultScopedCacheServiceBuilder;
import org.navistack.framework.cache.RedisCacheService;
import org.navistack.framework.cache.ScopedCacheServiceBuilder;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisOperations;

@Configuration
@ConditionalOnClass(CacheService.class)
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class CacheAutoConfiguration {

    @Bean
    @ConditionalOnBean(RedisOperations.class)
    @ConditionalOnMissingBean(CacheService.class)
    public CacheService cacheService(RedisOperations<String, Object> redisOperations) {
        return new RedisCacheService(redisOperations);
    }

    @Bean
    @ConditionalOnBean(CacheService.class)
    @ConditionalOnMissingBean(ScopedCacheServiceBuilder.class)
    public ScopedCacheServiceBuilder scopedCacheServiceBuilder(CacheService cacheService) {
        return new DefaultScopedCacheServiceBuilder(cacheService);
    }
}
