package org.navistack.framework.cache;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class DefaultScopedCacheStoreBuilderTest {
    @Test
    void shouldBuildScopedCacheStoreWhenCacheStoreAndScopeProvided() {
        CacheStore underlyingCacheStore = mock(CacheStore.class);
        DefaultScopedCacheStoreBuilder cacheStoreBuilder = new DefaultScopedCacheStoreBuilder()
                .cacheStore(underlyingCacheStore);
        ScopedCacheStore cacheStore = cacheStoreBuilder.build("test");
        assertThat(cacheStore)
                .isNotNull();
        assertThat(cacheStore)
                .isInstanceOf(ScopedCacheStore.class);
        CacheScope cacheScope = cacheStore.getCacheScope();
        assertThat(cacheScope)
                .isNotNull()
                .isInstanceOf(CacheScope.class)
                .extracting(CacheScope::getName)
                .isEqualTo("test");
        assertThat(cacheScope)
                .isNotNull()
                .isInstanceOf(CacheScope.class)
                .extracting(CacheScope::getDelimiter)
                .isEqualTo(":");
    }

    @Test
    void shouldThrowIllegalStateExceptionWhenCacheScopeIsNull() {
        CacheStore underlyingCacheStore = mock(CacheStore.class);
        DefaultScopedCacheStoreBuilder cacheStoreBuilder = new DefaultScopedCacheStoreBuilder()
                .cacheStore(underlyingCacheStore);

        assertThatThrownBy(() -> cacheStoreBuilder.build((CacheScope) null))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("cacheScope must not be null");
    }

    @Test
    void shouldThrowIllegalStateExceptionWhenCacheStoreIsNull() {
        DefaultScopedCacheStoreBuilder cacheStoreBuilder = new DefaultScopedCacheStoreBuilder();

        assertThatThrownBy(() -> cacheStoreBuilder.build("test"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("cacheStore must not be null");
    }
}
