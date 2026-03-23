package org.navistack.boot.cache.autoconfigure;

import org.junit.jupiter.api.Test;
import org.navistack.framework.cache.CacheService;
import org.navistack.framework.cache.DefaultScopedCacheServiceBuilder;
import org.navistack.framework.cache.HashMapCacheService;
import org.navistack.framework.cache.RedisCacheService;
import org.navistack.framework.cache.ScopedCacheServiceBuilder;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.data.redis.core.RedisOperations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class CacheAutoConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(CacheAutoConfiguration.class));

    @Test
    void shouldUseRedisCacheServiceWhenRedisOperationsBeanExists() {
        contextRunner.withBean(RedisOperations.class, () -> mock(RedisOperations.class))
                .run(context -> {
                    assertThat(context)
                            .hasSingleBean(CacheService.class);
                    assertThat(context.getBean(CacheService.class))
                            .isInstanceOf(RedisCacheService.class);
                    assertThat(context.getBean(ScopedCacheServiceBuilder.class))
                            .isInstanceOf(DefaultScopedCacheServiceBuilder.class);
                });
    }

    @Test
    void shouldUseHashMapCacheServiceWhenRedisOperationsBeanDoesNotExist() {
        contextRunner.run(context -> {
            assertThat(context)
                    .hasSingleBean(CacheService.class);
            assertThat(context.getBean(CacheService.class))
                    .isInstanceOf(HashMapCacheService.class);
            assertThat(context.getBean(ScopedCacheServiceBuilder.class))
                    .isInstanceOf(DefaultScopedCacheServiceBuilder.class);
        });
    }

}
