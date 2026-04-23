package org.navistack.framework.cache;

import lombok.Getter;
import lombok.NonNull;

import java.time.Duration;

@Getter
public class DefaultHierarchicalCacheStore implements HierarchicalCacheStore {

    private final CacheStore parentCacheStore;

    private final CacheScope cacheScope;

    public DefaultHierarchicalCacheStore(@NonNull CacheScope cacheScope, @NonNull CacheStore parentCacheStore) {
        this.parentCacheStore = parentCacheStore;
        this.cacheScope = cacheScope;
    }

    @Override
    public void set(String key, Object value) {
        String scopedKey = cacheScope.key(key);
        parentCacheStore.set(scopedKey, value);
    }

    @Override
    public void set(String key, Object value, Duration timeout) {
        String scopedKey = cacheScope.key(key);
        parentCacheStore.set(scopedKey, value, timeout);
    }

    @Override
    public boolean setIfAbsent(String key, Object value) {
        String scopedKey = cacheScope.key(key);
        return parentCacheStore.setIfAbsent(scopedKey, value);
    }

    @Override
    public boolean setIfAbsent(String key, Object value, Duration timeout) {
        String scopedKey = cacheScope.key(key);
        return parentCacheStore.setIfAbsent(scopedKey, value, timeout);
    }

    @Override
    public <V> V get(String key, Class<V> clazz) {
        String scopedKey = cacheScope.key(key);
        return parentCacheStore.get(scopedKey, clazz);
    }

    @Override
    public boolean delete(String key) {
        String scopedKey = cacheScope.key(key);
        return parentCacheStore.delete(scopedKey);
    }

    @Override
    public <V> V getAndDelete(String key, Class<V> clazz) {
        String scopedKey = cacheScope.key(key);
        return parentCacheStore.getAndDelete(scopedKey, clazz);
    }
}
