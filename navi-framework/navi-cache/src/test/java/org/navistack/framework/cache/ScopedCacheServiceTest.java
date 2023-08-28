package org.navistack.framework.cache;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ScopedCacheServiceTest {
    private CacheService underlyingService;

    private ScopedCacheService cacheService;

    @BeforeAll
    void beforeAll() {
        underlyingService = mock(CacheService.class);
        PrefixedCacheKeyBuilder keyBuilder = new PrefixedCacheKeyBuilder(".", "test");
        cacheService = new ScopedCacheService(underlyingService, keyBuilder);
    }

    @Test
    void set() {
        cacheService.set("key", "value");
        verify(underlyingService).
                set("test.key", "value");
    }

    @Test
    void setWithDuration() {
        cacheService.set("key", "value", Duration.ZERO);
        verify(underlyingService).
                set("test.key", "value", Duration.ZERO);
    }

    @Test
    void setWithTimeUnit() {
        cacheService.set("key", "value", 0, TimeUnit.SECONDS);
        verify(underlyingService).
                set("test.key", "value", 0, TimeUnit.SECONDS);
    }

    @Test
    void setIfAbsent() {
        cacheService.setIfAbsent("key", "value");
        verify(underlyingService).
                setIfAbsent("test.key", "value");
    }

    @Test
    void setIfAbsentWithDuration() {
        cacheService.setIfAbsent("key", "value", Duration.ZERO);
        verify(underlyingService).
                setIfAbsent("test.key", "value", Duration.ZERO);
    }

    @Test
    void setIfAbsentWithTimeUnit() {
        cacheService.setIfAbsent("key", "value", 0, TimeUnit.SECONDS);
        verify(underlyingService).
                setIfAbsent("test.key", "value", 0, TimeUnit.SECONDS);
    }

    @Test
    void get() {
        cacheService.get("key", String.class);
        verify(underlyingService).
                get("test.key", String.class);
    }

    @Test
    void delete() {
        cacheService.delete("key");
        verify(underlyingService).
                delete("test.key");
    }

    @Test
    void getAndDelete() {
        cacheService.getAndDelete("key", String.class);
        verify(underlyingService).
                getAndDelete("test.key", String.class);
    }
}
