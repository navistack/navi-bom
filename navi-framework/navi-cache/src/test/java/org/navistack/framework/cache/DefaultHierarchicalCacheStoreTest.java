package org.navistack.framework.cache;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.Duration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefaultHierarchicalCacheStoreTest {

    private CacheStore underlyingStore;

    private DefaultHierarchicalCacheStore defaultHierarchicalCacheStore;

    @BeforeAll
    void beforeAll() {
        underlyingStore = mock(CacheStore.class);

        CacheScope cacheScope = CacheScope.of("test");
        defaultHierarchicalCacheStore = new DefaultHierarchicalCacheStore(cacheScope, underlyingStore);
    }

    @Test
    void shouldDelegateSetWithScopedKeyWhenSetCalled() {
        defaultHierarchicalCacheStore.set("key", "value");
        verify(underlyingStore).
                set("test:key", "value");
    }

    @Test
    void shouldDelegateSetWithTimeoutWithScopedKeyWhenSetCalled() {
        defaultHierarchicalCacheStore.set("key", "value", Duration.ZERO);
        verify(underlyingStore).
                set("test:key", "value", Duration.ZERO);
    }

    @Test
    void shouldDelegateSetIfAbsentWithScopedKeyWhenSetIfAbsentCalled() {
        defaultHierarchicalCacheStore.setIfAbsent("key", "value");
        verify(underlyingStore).
                setIfAbsent("test:key", "value");
    }

    @Test
    void shouldDelegateSetIfAbsentWithTimeoutWithScopedKeyWhenSetIfAbsentCalled() {
        defaultHierarchicalCacheStore.setIfAbsent("key", "value", Duration.ZERO);
        verify(underlyingStore).
                setIfAbsent("test:key", "value", Duration.ZERO);
    }

    @Test
    void shouldDelegateGetWithScopedKeyWhenGetCalled() {
        defaultHierarchicalCacheStore.get("key", String.class);
        verify(underlyingStore).
                get("test:key", String.class);
    }

    @Test
    void shouldDelegateDeleteWithScopedKeyWhenDeleteCalled() {
        defaultHierarchicalCacheStore.delete("key");
        verify(underlyingStore).
                delete("test:key");
    }

    @Test
    void shouldDelegateGetAndDeleteWithScopedKeyWhenGetAndDeleteCalled() {
        defaultHierarchicalCacheStore.getAndDelete("key", String.class);
        verify(underlyingStore).
                getAndDelete("test:key", String.class);
    }
}
