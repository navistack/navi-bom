package org.navistack.boot.cache.autoconfigure;

import org.navistack.framework.cache.CacheStore;
import org.navistack.framework.cache.DefaultHierarchicalCacheStoreBuilder;
import org.navistack.framework.cache.HashMapCacheStore;
import org.navistack.framework.cache.HierarchicalCacheStoreBuilder;
import org.navistack.framework.cache.RedisOperationsCacheStore;
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
    @ConditionalOnMissingBean(HierarchicalCacheStoreBuilder.class)
    public HierarchicalCacheStoreBuilder hierarchicalCacheStoreBuilder(CacheStore cacheStore) {
        return new DefaultHierarchicalCacheStoreBuilder()
                .cacheStore(cacheStore);
    }

    @Configuration
    @ConditionalOnClass(RedisOperations.class)
    static class RedisCacheServiceConfiguration {

        @Bean
        @ConditionalOnBean(RedisOperations.class)
        @ConditionalOnMissingBean(CacheStore.class)
        RedisOperationsCacheStore redisOperationsCacheStore(
                RedisOperations<String, Object> redisOperations) {
            return new RedisOperationsCacheStore(redisOperations);
        }
    }
}
