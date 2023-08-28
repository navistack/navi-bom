package org.navistack.framework.cache;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class DefaultScopedCacheServiceBuilderTest {
    @Test
    void shouldBuildSuccessfully() {
        CacheService underlyingService = mock(CacheService.class);
        DefaultScopedCacheServiceBuilder cacheServiceBuilder = new DefaultScopedCacheServiceBuilder(underlyingService);
        CacheService cacheService = cacheServiceBuilder.build("test");
        assertThat(cacheService)
                .isNotNull();
        assertThat(cacheService)
                .isInstanceOf(ScopedCacheService.class);
        CacheKeyBuilder keyBuilder = ((ScopedCacheService) cacheService).getKeyBuilder();
        assertThat(keyBuilder)
                .isNotNull()
                .isInstanceOf(PrefixedCacheKeyBuilder.class);
        String prefix = ((PrefixedCacheKeyBuilder) keyBuilder).getPrefix();
        assertThat(prefix)
                .isNotNull()
                .isEqualTo("test");
        String delimiter = ((PrefixedCacheKeyBuilder) keyBuilder).getDelimiter();
        assertThat(delimiter)
                .isNotNull()
                .isEqualTo(".");
    }

    @Test
    void shouldReturnSameInstanceForSameNamespace() {
        CacheService underlyingService = mock(CacheService.class);
        DefaultScopedCacheServiceBuilder cacheServiceBuilder = new DefaultScopedCacheServiceBuilder(underlyingService);
        CacheService cacheService1 = cacheServiceBuilder.build("test");
        CacheService cacheService2 = cacheServiceBuilder.build("test");
        assertThat(cacheService1)
                .isNotNull();
        assertThat(cacheService2)
                .isNotNull()
                .isSameAs(cacheService1);
    }
}
