package org.navistack.framework.cache;

import lombok.Getter;
import lombok.NonNull;

import java.time.Duration;

@Getter
public class ScopedCacheStore implements CacheStore {

    private final CacheStore cacheStore;

    private final CacheScope cacheScope;

    public ScopedCacheStore(@NonNull CacheStore cacheStore, @NonNull CacheScope cacheScope) {
        this.cacheStore = cacheStore;
        this.cacheScope = cacheScope;
    }

    @Override
    public void set(String key, Object value) {
        String scopedKey = cacheScope.key(key);
        cacheStore.set(scopedKey, value);
    }

    @Override
    public void set(String key, Object value, Duration timeout) {
        String scopedKey = cacheScope.key(key);
        cacheStore.set(scopedKey, value, timeout);
    }

    @Override
    public boolean setIfAbsent(String key, Object value) {
        String scopedKey = cacheScope.key(key);
        return cacheStore.setIfAbsent(scopedKey, value);
    }

    @Override
    public boolean setIfAbsent(String key, Object value, Duration timeout) {
        String scopedKey = cacheScope.key(key);
        return cacheStore.setIfAbsent(scopedKey, value, timeout);
    }

    @Override
    public <V> V get(String key, Class<V> clazz) {
        String scopedKey = cacheScope.key(key);
        return cacheStore.get(scopedKey, clazz);
    }

    @Override
    public boolean delete(String key) {
        String scopedKey = cacheScope.key(key);
        return cacheStore.delete(scopedKey);
    }

    @Override
    public <V> V getAndDelete(String key, Class<V> clazz) {
        String scopedKey = cacheScope.key(key);
        return cacheStore.getAndDelete(scopedKey, clazz);
    }
}
