package org.navistack.framework.cache;

import java.time.Duration;

public interface CacheService {
    void set(String key, Object value);

    void set(String key, Object value, Duration timeout);

    boolean setIfAbsent(String key, Object value);

    boolean setIfAbsent(String key, Object value, Duration timeout);

    <V> V get(String key, Class<V> clazz);

    boolean delete(String key);

    <V> V getAndDelete(String key, Class<V> clazz);
}
