package org.navistack.framework.cache;

import org.springframework.data.redis.core.TimeoutUtils;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public interface KvCacheService {
    <K, V> void set(K key, V value);

    <K, V> void set(K key, V value, Duration timeout);

    <K, V> void set(K key, V value, long timeout, TimeUnit unit);

    <K, V> boolean setIfAbsent(K key, V value);

    <K, V> boolean setIfAbsent(K key, V value, long timeout, TimeUnit unit);

    default <K, V> boolean setIfAbsent(K key, V value, Duration timeout) {
        if (timeout == null) {
            throw new IllegalArgumentException("Timeout must not be null!");
        }

        if (TimeoutUtils.hasMillis(timeout)) {
            return setIfAbsent(key, value, timeout.toMillis(), TimeUnit.MILLISECONDS);
        }

        return setIfAbsent(key, value, timeout.getSeconds(), TimeUnit.SECONDS);
    }

    <V> V get(Object key, Class<V> clazz);

    boolean delete(Object key);

    <V> V getAndDelete(Object key, Class<V> clazz);
}
