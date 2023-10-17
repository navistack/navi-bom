package org.navistack.framework.cache;

import lombok.Getter;
import org.navistack.framework.utils.Asserts;

import java.time.Duration;

@Getter
public class ScopedCacheService implements CacheService {
    private final CacheService underlyingService;

    private final CacheKeyBuilder keyBuilder;

    public ScopedCacheService(CacheService underlyingService, CacheKeyBuilder keyBuilder) {
        Asserts.notNull(underlyingService, "underlyingService must not be null");
        Asserts.notNull(keyBuilder, "keyBuilder must not be null");
        this.underlyingService = underlyingService;
        this.keyBuilder = keyBuilder;
    }

    @Override
    public void set(String key, Object value) {
        String scopedKey = keyBuilder.build(key);
        underlyingService.set(scopedKey, value);
    }

    @Override
    public void set(String key, Object value, Duration timeout) {
        String scopedKey = keyBuilder.build(key);
        underlyingService.set(scopedKey, value, timeout);
    }

    @Override
    public boolean setIfAbsent(String key, Object value) {
        String scopedKey = keyBuilder.build(key);
        return underlyingService.setIfAbsent(scopedKey, value);
    }

    @Override
    public boolean setIfAbsent(String key, Object value, Duration timeout) {
        String scopedKey = keyBuilder.build(key);
        return underlyingService.setIfAbsent(scopedKey, value, timeout);
    }

    @Override
    public <V> V get(String key, Class<V> clazz) {
        String scopedKey = keyBuilder.build(key);
        return underlyingService.get(scopedKey, clazz);
    }

    @Override
    public boolean delete(String key) {
        String scopedKey = keyBuilder.build(key);
        return underlyingService.delete(scopedKey);
    }

    @Override
    public <V> V getAndDelete(String key, Class<V> clazz) {
        String scopedKey = keyBuilder.build(key);
        return underlyingService.getAndDelete(scopedKey, clazz);
    }
}
