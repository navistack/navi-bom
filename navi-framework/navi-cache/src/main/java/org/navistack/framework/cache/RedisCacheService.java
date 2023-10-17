package org.navistack.framework.cache;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;

import java.time.Duration;

public class RedisCacheService implements CacheService {
    private final RedisOperations<String, Object> redisOperations;

    public RedisCacheService(RedisOperations<String, Object> redisOperations) {
        this.redisOperations = redisOperations;
    }

    @Override
    public void set(String key, Object value) {
        redisOperations.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, Object value, Duration timeout) {
        redisOperations.opsForValue().set(key, value, timeout);
    }

    @Override
    public boolean setIfAbsent(String key, Object value) {
        Boolean result = redisOperations.opsForValue().setIfAbsent(key, value);
        return result != null && result;
    }

    @Override
    public boolean setIfAbsent(String key, Object value, Duration timeout) {
        Boolean result = redisOperations.opsForValue().setIfAbsent(key, value, timeout);
        return result != null && result;
    }

    @Override
    public <V> V get(String key, Class<V> clazz) {
        return clazz.cast(redisOperations.opsForValue().get(key));
    }

    @Override
    public boolean delete(String key) {
        Boolean result = redisOperations.delete(key);
        return result != null && result;
    }

    public <V> V getAndDelete(String key, Class<V> clazz) {
        return clazz.cast(redisOperations.execute(new SessionCallback<>() {
            @Override
            public <K, TV> Object execute(RedisOperations<K, TV> pOperations) throws DataAccessException {
                @SuppressWarnings("unchecked")
                RedisOperations<String, Object> operations = (RedisOperations<String, Object>) pOperations;
                Object value = operations.opsForValue().get(key);
                operations.delete(key);
                return value;
            }
        }));
    }
}
