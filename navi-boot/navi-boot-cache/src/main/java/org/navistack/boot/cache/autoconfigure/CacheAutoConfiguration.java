package org.navistack.boot.cache.autoconfigure;

import org.navistack.framework.cache.CacheStore;
import org.navistack.framework.cache.DefaultScopedCacheStoreBuilder;
import org.navistack.framework.cache.HashMapCacheStore;
import org.navistack.framework.cache.RedisCacheStore;
import org.navistack.framework.cache.ScopedCacheStoreBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisOperations;

@Configuration
@ConditionalOnClass(CacheStore.class)
public class CacheAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(CacheStore.class)
    public HashMapCacheStore hashMapCacheService() {
        return new HashMapCacheStore();
    }

    @Bean
    @ConditionalOnMissingBean(ScopedCacheStoreBuilder.class)
    public ScopedCacheStoreBuilder scopedCacheServiceBuilder(CacheStore cacheStore) {
        return new DefaultScopedCacheStoreBuilder()
                .cacheStore(cacheStore);
    }

    @Configuration
    @ConditionalOnClass(RedisOperations.class)
    static class RedisCacheServiceConfiguration {

        @Bean
        @ConditionalOnBean(RedisOperations.class)
        @ConditionalOnMissingBean(CacheStore.class)
        RedisCacheStore redisCacheService(
                RedisOperations<String, Object> redisOperations) {
            return new RedisCacheStore(redisOperations);
        }
    }
}
