package org.navistack.framework.cache;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class DefaultDefaultHierarchicalCacheStoreBuilderTest {
    @Test
    void shouldBuildHierarchicalCacheStoreWhenCacheStoreAndScopeProvided() {
        CacheStore underlyingCacheStore = mock(CacheStore.class);
        DefaultHierarchicalCacheStoreBuilder cacheStoreBuilder = new DefaultHierarchicalCacheStoreBuilder()
                .cacheStore(underlyingCacheStore);
        DefaultHierarchicalCacheStore cacheStore = cacheStoreBuilder.build("test");
        assertThat(cacheStore)
                .isNotNull();
        assertThat(cacheStore)
                .isInstanceOf(DefaultHierarchicalCacheStore.class);
        CacheScope cacheScope = cacheStore.cacheScope();
        assertThat(cacheScope)
                .isNotNull()
                .isInstanceOf(CacheScope.class)
                .extracting(CacheScope::name)
                .isEqualTo("test");
        assertThat(cacheScope)
                .isNotNull()
                .isInstanceOf(CacheScope.class)
                .extracting(CacheScope::delimiter)
                .isEqualTo(":");
    }

    @Test
    void shouldThrowIllegalStateExceptionWhenCacheScopeIsNull() {
        CacheStore underlyingCacheStore = mock(CacheStore.class);
        DefaultHierarchicalCacheStoreBuilder cacheStoreBuilder = new DefaultHierarchicalCacheStoreBuilder()
                .cacheStore(underlyingCacheStore);

        assertThatThrownBy(() -> cacheStoreBuilder.build((CacheScope) null))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("cacheScope must not be null");
    }

    @Test
    void shouldThrowIllegalStateExceptionWhenCacheStoreIsNull() {
        DefaultHierarchicalCacheStoreBuilder cacheStoreBuilder = new DefaultHierarchicalCacheStoreBuilder();

        assertThatThrownBy(() -> cacheStoreBuilder.build("test"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("cacheStore must not be null");
    }
}
