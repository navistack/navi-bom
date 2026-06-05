package org.navistack.framework.cache;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DefaultHierarchicalCacheStoreTest {

    @Test
    void shouldReturnParentCacheStoreWhenGetParentCacheStore() {
        CacheStore underlyingStore = mock(CacheStore.class);
        DefaultHierarchicalCacheStore store = new DefaultHierarchicalCacheStore(CacheScope.of("test"), underlyingStore);
        assertThat(store.parentCacheStore()).isSameAs(underlyingStore);
    }

    @Test
    void shouldUseScopedKeyWhenSet() {
        CacheStore underlyingStore = mock(CacheStore.class);
        DefaultHierarchicalCacheStore store = new DefaultHierarchicalCacheStore(CacheScope.of("test"), underlyingStore);
        store.set("key", "value");
        verify(underlyingStore).set("test:key", "value");
    }

    @Test
    void shouldUseScopedKeyWhenSetWithTimeout() {
        CacheStore underlyingStore = mock(CacheStore.class);
        DefaultHierarchicalCacheStore store = new DefaultHierarchicalCacheStore(CacheScope.of("test"), underlyingStore);
        store.set("key", "value", Duration.ZERO);
        verify(underlyingStore).set("test:key", "value", Duration.ZERO);
    }

    @Test
    void shouldUseScopedKeyWhenSetIfAbsent() {
        CacheStore underlyingStore = mock(CacheStore.class);
        DefaultHierarchicalCacheStore store = new DefaultHierarchicalCacheStore(CacheScope.of("test"), underlyingStore);
        store.setIfAbsent("key", "value");
        verify(underlyingStore).setIfAbsent("test:key", "value");
    }

    @Test
    void shouldReturnResultWhenSetIfAbsent() {
        CacheStore underlyingStore = mock(CacheStore.class);
        DefaultHierarchicalCacheStore store = new DefaultHierarchicalCacheStore(CacheScope.of("test"), underlyingStore);
        when(underlyingStore.setIfAbsent("test:key", "value")).thenReturn(true);
        assertThat(store.setIfAbsent("key", "value")).isTrue();
    }

    @Test
    void shouldUseScopedKeyWhenSetIfAbsentWithTimeout() {
        CacheStore underlyingStore = mock(CacheStore.class);
        DefaultHierarchicalCacheStore store = new DefaultHierarchicalCacheStore(CacheScope.of("test"), underlyingStore);
        store.setIfAbsent("key", "value", Duration.ZERO);
        verify(underlyingStore).setIfAbsent("test:key", "value", Duration.ZERO);
    }

    @Test
    void shouldReturnResultWhenSetIfAbsentWithTimeout() {
        CacheStore underlyingStore = mock(CacheStore.class);
        DefaultHierarchicalCacheStore store = new DefaultHierarchicalCacheStore(CacheScope.of("test"), underlyingStore);
        when(underlyingStore.setIfAbsent("test:key", "value", Duration.ZERO)).thenReturn(true);
        assertThat(store.setIfAbsent("key", "value", Duration.ZERO)).isTrue();
    }

    @Test
    void shouldUseScopedKeyWhenGet() {
        CacheStore underlyingStore = mock(CacheStore.class);
        DefaultHierarchicalCacheStore store = new DefaultHierarchicalCacheStore(CacheScope.of("test"), underlyingStore);
        store.get("key", String.class);
        verify(underlyingStore).get("test:key", String.class);
    }

    @Test
    void shouldReturnValueWhenGet() {
        CacheStore underlyingStore = mock(CacheStore.class);
        DefaultHierarchicalCacheStore store = new DefaultHierarchicalCacheStore(CacheScope.of("test"), underlyingStore);
        when(underlyingStore.get("test:key", String.class)).thenReturn("value");
        assertThat(store.get("key", String.class)).isEqualTo("value");
    }

    @Test
    void shouldUseScopedKeyWhenDelete() {
        CacheStore underlyingStore = mock(CacheStore.class);
        DefaultHierarchicalCacheStore store = new DefaultHierarchicalCacheStore(CacheScope.of("test"), underlyingStore);
        store.delete("key");
        verify(underlyingStore).delete("test:key");
    }

    @Test
    void shouldReturnResultWhenDelete() {
        CacheStore underlyingStore = mock(CacheStore.class);
        DefaultHierarchicalCacheStore store = new DefaultHierarchicalCacheStore(CacheScope.of("test"), underlyingStore);
        when(underlyingStore.delete("test:key")).thenReturn(true);
        assertThat(store.delete("key")).isTrue();
    }

    @Test
    void shouldUseScopedKeyWhenGetAndDelete() {
        CacheStore underlyingStore = mock(CacheStore.class);
        DefaultHierarchicalCacheStore store = new DefaultHierarchicalCacheStore(CacheScope.of("test"), underlyingStore);
        store.getAndDelete("key", String.class);
        verify(underlyingStore).getAndDelete("test:key", String.class);
    }

    @Test
    void shouldReturnValueWhenGetAndDelete() {
        CacheStore underlyingStore = mock(CacheStore.class);
        DefaultHierarchicalCacheStore store = new DefaultHierarchicalCacheStore(CacheScope.of("test"), underlyingStore);
        when(underlyingStore.getAndDelete("test:key", String.class)).thenReturn("value");
        assertThat(store.getAndDelete("key", String.class)).isEqualTo("value");
    }
}
