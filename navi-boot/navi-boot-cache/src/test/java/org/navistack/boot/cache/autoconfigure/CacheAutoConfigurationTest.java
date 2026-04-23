package org.navistack.boot.cache.autoconfigure;

import org.junit.jupiter.api.Test;
import org.navistack.framework.cache.CacheStore;
import org.navistack.framework.cache.DefaultHierarchicalCacheStoreBuilder;
import org.navistack.framework.cache.HashMapCacheStore;
import org.navistack.framework.cache.RedisOperationsCacheStore;
import org.navistack.framework.cache.HierarchicalCacheStoreBuilder;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.data.redis.core.RedisOperations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class CacheAutoConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(CacheAutoConfiguration.class));

    @Test
    void shouldUseRedisCacheStoreWhenRedisOperationsBeanExists() {
        contextRunner.withBean(RedisOperations.class, () -> mock(RedisOperations.class))
                .run(context -> {
                    assertThat(context)
                            .hasSingleBean(CacheStore.class);
                    assertThat(context.getBean(CacheStore.class))
                            .isInstanceOf(RedisOperationsCacheStore.class);
                    assertThat(context.getBean(HierarchicalCacheStoreBuilder.class))
                            .isInstanceOf(DefaultHierarchicalCacheStoreBuilder.class);
                });
    }

    @Test
    void shouldUseHashMapCacheStoreWhenRedisOperationsBeanDoesNotExist() {
        contextRunner.run(context -> {
            assertThat(context)
                    .hasSingleBean(CacheStore.class);
            assertThat(context.getBean(CacheStore.class))
                    .isInstanceOf(HashMapCacheStore.class);
            assertThat(context.getBean(HierarchicalCacheStoreBuilder.class))
                    .isInstanceOf(DefaultHierarchicalCacheStoreBuilder.class);
        });
    }

    @Test
    void shouldNotOverrideUserProvidedCacheStoreWhenCacheStoreBeanExists() {
        CacheStore userCacheStore = mock(CacheStore.class);
        contextRunner.withBean(CacheStore.class, () -> userCacheStore)
                .run(context -> {
                    assertThat(context).hasSingleBean(CacheStore.class);
                    assertThat(context.getBean(CacheStore.class)).isSameAs(userCacheStore);
                    assertThat(context.getBeansOfType(HashMapCacheStore.class)).isEmpty();
                    assertThat(context.getBeansOfType(RedisOperationsCacheStore.class)).isEmpty();
                });
    }

    @Test
    void shouldNotOverrideUserProvidedHierarchicalCacheStoreBuilderWhenBuilderBeanExists() {
        HierarchicalCacheStoreBuilder userBuilder = mock(HierarchicalCacheStoreBuilder.class);
        contextRunner.withBean(HierarchicalCacheStoreBuilder.class, () -> userBuilder)
                .run(context -> {
                    assertThat(context).hasSingleBean(HierarchicalCacheStoreBuilder.class);
                    assertThat(context.getBean(HierarchicalCacheStoreBuilder.class)).isSameAs(userBuilder);
                });
    }

}
