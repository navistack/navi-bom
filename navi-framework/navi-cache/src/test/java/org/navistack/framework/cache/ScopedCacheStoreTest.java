package org.navistack.framework.cache;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.Duration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ScopedCacheStoreTest {

    private CacheStore underlyingStore;

    private ScopedCacheStore scopedCacheStore;

    @BeforeAll
    void beforeAll() {
        underlyingStore = mock(CacheStore.class);

        CacheScope cacheScope = CacheScope.of("test");
        scopedCacheStore = new ScopedCacheStore(underlyingStore, cacheScope);
    }

    @Test
    void shouldDelegateSetWithScopedKeyWhenSetCalled() {
        scopedCacheStore.set("key", "value");
        verify(underlyingStore).
                set("test:key", "value");
    }

    @Test
    void shouldDelegateSetWithTimeoutWithScopedKeyWhenSetCalled() {
        scopedCacheStore.set("key", "value", Duration.ZERO);
        verify(underlyingStore).
                set("test:key", "value", Duration.ZERO);
    }

    @Test
    void shouldDelegateSetIfAbsentWithScopedKeyWhenSetIfAbsentCalled() {
        scopedCacheStore.setIfAbsent("key", "value");
        verify(underlyingStore).
                setIfAbsent("test:key", "value");
    }

    @Test
    void shouldDelegateSetIfAbsentWithTimeoutWithScopedKeyWhenSetIfAbsentCalled() {
        scopedCacheStore.setIfAbsent("key", "value", Duration.ZERO);
        verify(underlyingStore).
                setIfAbsent("test:key", "value", Duration.ZERO);
    }

    @Test
    void shouldDelegateGetWithScopedKeyWhenGetCalled() {
        scopedCacheStore.get("key", String.class);
        verify(underlyingStore).
                get("test:key", String.class);
    }

    @Test
    void shouldDelegateDeleteWithScopedKeyWhenDeleteCalled() {
        scopedCacheStore.delete("key");
        verify(underlyingStore).
                delete("test:key");
    }

    @Test
    void shouldDelegateGetAndDeleteWithScopedKeyWhenGetAndDeleteCalled() {
        scopedCacheStore.getAndDelete("key", String.class);
        verify(underlyingStore).
                getAndDelete("test:key", String.class);
    }
}
