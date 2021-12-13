package org.navistack.framework.cache;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public interface KvCacheService {
    <K, V> void set(K key, V value);

    <K, V> void set(K key, V value, Duration timeout);

    <K, V> void set(K key, V value, long timeout, TimeUnit unit);

    <V> V get(Object key, Class<V> clazz);

    boolean delete(Object key);

    <V> V getAndDelete(Object key, Class<V> clazz);
}
