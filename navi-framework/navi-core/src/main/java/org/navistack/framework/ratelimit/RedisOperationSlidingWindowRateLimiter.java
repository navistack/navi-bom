package org.navistack.framework.ratelimit;

import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.script.RedisScript;

import java.time.Duration;
import java.util.Collections;

public class RedisOperationSlidingWindowRateLimiter extends AbstractRedisSlidingWindowRateLimiter {
    private final RedisOperations<String, Long> redisOperations;

    public RedisOperationSlidingWindowRateLimiter(RedisOperations<String, Long> redisOperations) {
        this.redisOperations = redisOperations;
    }

    @Override
    protected boolean executeScript(Resource scriptResource, String key, int maxRequests, long timestamp, Duration windowSize) {
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
