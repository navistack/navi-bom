package org.navistack.framework.cache;

import lombok.Getter;
import lombok.Setter;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class HashMapCacheService implements CacheService {
    private final Map<String, Object> objectMap = new HashMap<>();
    private final Map<String, Long> expirationMap = new HashMap<>();

    @Getter
    @Setter
    private transient Clock clock = Clock.system(ZoneId.systemDefault());

    @Override
    public synchronized void set(String key, Object value) {
        objectMap.put(key, value);
    }

    @Override
    public synchronized void set(String key, Object value, Duration timeout) {
        long expiration = Instant.now(clock).plus(timeout).toEpochMilli();
        expirationMap.put(key, expiration);
        objectMap.put(key, value);
    }

    @Override
    public synchronized boolean setIfAbsent(String key, Object value) {
        removeIfExpired(key, Instant.now(clock));
        if (objectMap.containsKey(key)) {
            return false;
        }
        set(key, value);
        return true;
    }

    @Override
    public boolean setIfAbsent(String key, Object value, Duration timeout) {
        removeIfExpired(key, Instant.now(clock));
        if (objectMap.containsKey(key)) {
            return false;
        }
        set(key, value, timeout);
        return true;
    }

    @Override
    public synchronized <V> V get(String key, Class<V> clazz) {
        removeIfExpired(key, Instant.now(clock));
        return clazz.cast(objectMap.get(key));
    }

    @Override
    public synchronized boolean delete(String key) {
        expirationMap.remove(key);
        objectMap.remove(key);
        return true;
    }

    @Override
    public synchronized <V> V getAndDelete(String key, Class<V> clazz) {
        removeIfExpired(key, Instant.now(clock));
        return clazz.cast(objectMap.remove(key));
    }

    private void removeIfExpired(String key, Instant now) {
        long nowMilli = now.toEpochMilli();
        Long expiration = expirationMap.get(key);
        if (expiration != null && expiration < nowMilli) {
            expirationMap.remove(key);
            objectMap.remove(key);
        }
    }
}
