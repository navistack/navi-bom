package org.navistack.framework.cache;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RedisOperationsCacheStoreTest {

    @Test
    @SuppressWarnings("unchecked")
    void shouldSetValueWhenSetCalled() {
        RedisOperations<String, Object> redisOperations = mock(RedisOperations.class);
        ValueOperations<String, Object> valueOperations = mock(ValueOperations.class);
        when(redisOperations.opsForValue()).thenReturn(valueOperations);

        RedisOperationsCacheStore store = new RedisOperationsCacheStore(redisOperations);
        store.set("key", "value");

        verify(valueOperations).set("key", "value");
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldSetValueWithTimeoutWhenSetCalledWithTimeout() {
        RedisOperations<String, Object> redisOperations = mock(RedisOperations.class);
        ValueOperations<String, Object> valueOperations = mock(ValueOperations.class);
        when(redisOperations.opsForValue()).thenReturn(valueOperations);

        RedisOperationsCacheStore store = new RedisOperationsCacheStore(redisOperations);
        store.set("key", "value", Duration.ofSeconds(1));

        verify(valueOperations).set("key", "value", Duration.ofSeconds(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldReturnFalseWhenSetIfAbsentReturnsNull() {
        RedisOperations<String, Object> redisOperations = mock(RedisOperations.class);
        ValueOperations<String, Object> valueOperations = mock(ValueOperations.class);
        when(redisOperations.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.setIfAbsent("key", "value")).thenReturn(null);

        RedisOperationsCacheStore store = new RedisOperationsCacheStore(redisOperations);
        assertThat(store.setIfAbsent("key", "value")).isFalse();
    }

    @Test
    void shouldReturnFalseWhenDeleteReturnsNull() {
        RedisOperations<String, Object> redisOperations = mock(RedisOperations.class);
        when(redisOperations.delete("key")).thenReturn(null);

        RedisOperationsCacheStore store = new RedisOperationsCacheStore(redisOperations);
        assertThat(store.delete("key")).isFalse();
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldGetAndDeleteValueWhenGetAndDeleteCalled() {
        RedisOperations<String, Object> redisOperations = mock(RedisOperations.class);
        RedisOperationsCacheStore store = new RedisOperationsCacheStore(redisOperations);

        ArgumentCaptor<org.springframework.data.redis.core.SessionCallback<Object>> captor =
                ArgumentCaptor.forClass(org.springframework.data.redis.core.SessionCallback.class);

        RedisOperations<String, Object> ops = mock(RedisOperations.class);
        ValueOperations<String, Object> valueOps = mock(ValueOperations.class);
        when(ops.opsForValue()).thenReturn(valueOps);
        when(valueOps.get("key")).thenReturn("value");

        when(redisOperations.execute(captor.capture())).thenAnswer(invocation -> {
            var callback = captor.getValue();
            return callback.execute(ops);
        });

        Object result = store.getAndDelete("key", Object.class);
        assertThat(result).isEqualTo("value");
        verify(ops).delete("key");
    }
}

