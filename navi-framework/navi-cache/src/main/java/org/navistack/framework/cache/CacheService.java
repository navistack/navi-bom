package org.navistack.framework.cache;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public interface CacheService {
    void set(String key, Object value);

    void set(String key, Object value, Duration timeout);

    void set(String key, Object value, long timeout, TimeUnit unit);

    boolean setIfAbsent(String key, Object value);

    boolean setIfAbsent(String key, Object value, long timeout, TimeUnit unit);

    default boolean setIfAbsent(String key, Object value, Duration timeout) {
        if (timeout == null) {
            throw new IllegalArgumentException("Timeout must not be null!");
        }

        // Check if the given timeout can be represented in sec or requires msec representation.
        if (timeout.toMillis() % 1000 != 0) {
            return setIfAbsent(key, value, timeout.toMillis(), TimeUnit.MILLISECONDS);
        }

        return setIfAbsent(key, value, timeout.getSeconds(), TimeUnit.SECONDS);
    }

    <V> V get(String key, Class<V> clazz);

    boolean delete(String key);

    <V> V getAndDelete(String key, Class<V> clazz);
}
