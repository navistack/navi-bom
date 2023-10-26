package org.navistack.framework.ratelimit;

import lombok.Getter;
import lombok.Setter;
import org.navistack.framework.cache.CacheKeyBuilder;
import org.navistack.framework.cache.PrefixedCacheKeyBuilder;
import org.navistack.framework.utils.Strings;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.script.RedisScript;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

@Getter
@Setter
public class RedisSlidingWindowRateLimiter implements SlidingWindowRateLimiter {
    private static final String DEFAULT_USER_KEY = "GLOBAL_RESOURCE";
    private static final Resource
            DEFAULT_SCRIPT_RESOURCE = new ClassPathResource("navi/scripts/slidingwindowratelimiter.lua");
    private static final CacheKeyBuilder
            KEY_BUILDER = new PrefixedCacheKeyBuilder(".", "NAVI", "SLIDING_WINDOW_RATE_LIMITER");
    private static final int DEFAULT_MAX_REQUESTS_OF_WINDOW = 1000;
    private static final int DEFAULT_SIZE_OF_WINDOW = 1000 /* ms */;

    private final RedisOperations<String, Long> redisOperations;

    private Resource scriptResource = DEFAULT_SCRIPT_RESOURCE;
    private CacheKeyBuilder keyBuilder = KEY_BUILDER;
    private int maxRequestsOfWindow = DEFAULT_MAX_REQUESTS_OF_WINDOW;
    private Duration sizeOfWindow = Duration.of(DEFAULT_SIZE_OF_WINDOW, ChronoUnit.MILLIS);

    public RedisSlidingWindowRateLimiter(RedisOperations<String, Long> redisOperations) {
        this.redisOperations = redisOperations;
    }

    @Override
    public boolean tryAcquire(String key) {
        return tryAcquire(key, getMaxRequestsOfWindow(), getSizeOfWindow());
    }

    @Override
    public boolean tryAcquire(String key, int maxRequests, Duration windowSize) {
        key = Strings.hasText(key) ? key : DEFAULT_USER_KEY;
        String scopedKey = keyBuilder.build(key);
        return executeScript(scriptResource, scopedKey, maxRequests, System.currentTimeMillis(), windowSize);
    }

    public void setKeyPrefix(String prefix) {
        if (!Strings.hasLength(prefix)) {
            throw new IllegalArgumentException("prefix can not be null or empty");
        }
        keyBuilder = new PrefixedCacheKeyBuilder(".", prefix);
    }

    private boolean executeScript(Resource scriptResource,
                                  String key,
                                  int maxRequests,
                                  long timestamp,
                                  Duration windowSize) {
        long windowSizeInMillis = windowSize.toMillis();
        Boolean result = redisOperations.execute(
                RedisScript.of(scriptResource, Boolean.class),
                Collections.singletonList(key),
                Integer.toString(maxRequests),
                Long.toString(timestamp),
                Long.toString(windowSizeInMillis)
        );
        return result != null && result;
    }
}
