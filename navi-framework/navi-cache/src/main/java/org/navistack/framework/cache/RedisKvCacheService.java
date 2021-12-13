package org.navistack.framework.cache;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RedisKvCacheService implements KvCacheService {
    private final RedisOperations<Object, Object> redisOperations;

    public RedisKvCacheService(RedisOperations<Object, Object> redisOperations) {
        this.redisOperations = redisOperations;
    }

    @Override
    public <K, V> void set(K key, V value) {
        redisOperations.opsForValue().set(key, value);
    }

    @Override
    public <K, V> void set(K key, V value, Duration timeout) {
        redisOperations.opsForValue().set(key, value, timeout);
    }

    @Override
    public <K, V> void set(K key, V value, long timeout, TimeUnit unit) {
        redisOperations.opsForValue().set(key, value, timeout, unit);
    }

    @Override
    public <V> V get(Object key, Class<V> clazz) {
        return clazz.cast(redisOperations.opsForValue().get(key));
    }

    @Override
    public boolean delete(Object key) {
        Boolean result = redisOperations.delete(key);
        return result != null && result;
    }

    public <V> V getAndDelete(Object key, Class<V> clazz) {
        return clazz.cast(redisOperations.execute(new SessionCallback<Object>() {
            @Override
            public <K, TV> Object execute(RedisOperations<K, TV> pOperations) throws DataAccessException {
                @SuppressWarnings("unchecked")
                RedisOperations<Object, Object> operations = (RedisOperations<Object, Object>) pOperations;
                Object value = operations.opsForValue().get(key);
                operations.delete(key);
                return value;
            }
        }));
    }
}
