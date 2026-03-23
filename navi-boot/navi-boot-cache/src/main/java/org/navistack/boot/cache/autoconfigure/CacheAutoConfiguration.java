package org.navistack.boot.cache.autoconfigure;

import org.navistack.framework.cache.CacheService;
import org.navistack.framework.cache.DefaultScopedCacheServiceBuilder;
import org.navistack.framework.cache.HashMapCacheService;
import org.navistack.framework.cache.RedisCacheService;
import org.navistack.framework.cache.ScopedCacheServiceBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisOperations;

@Configuration
@ConditionalOnClass(CacheService.class)
public class CacheAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(CacheService.class)
    public HashMapCacheService hashMapCacheService() {
        return new HashMapCacheService();
    }

    @Bean
    @ConditionalOnBean(CacheService.class)
    @ConditionalOnMissingBean(ScopedCacheServiceBuilder.class)
    public ScopedCacheServiceBuilder scopedCacheServiceBuilder(CacheService cacheService) {
        return new DefaultScopedCacheServiceBuilder(cacheService);
    }

    @Configuration
    @ConditionalOnClass(RedisOperations.class)
    static class RedisCacheServiceConfiguration {

        @Bean
        @ConditionalOnBean(RedisOperations.class)
        @ConditionalOnMissingBean(CacheService.class)
        RedisCacheService redisCacheService(
                RedisOperations<String, Object> redisOperations) {
            return new RedisCacheService(redisOperations);
        }
    }
}
