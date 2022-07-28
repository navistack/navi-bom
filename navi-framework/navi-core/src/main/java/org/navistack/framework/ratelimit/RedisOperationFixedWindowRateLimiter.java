package org.navistack.framework.ratelimit;

import com.sun.tools.javac.util.List;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.script.RedisScript;

import java.time.temporal.TemporalUnit;

public class RedisOperationFixedWindowRateLimiter extends AbstractRedisFixedWindowRateLimiter {
    private final RedisOperations<String, Long> redisOperations;

    public RedisOperationFixedWindowRateLimiter(RedisOperations<String, Long> redisOperations) {
        this.redisOperations = redisOperations;
    }

    @Override
    protected boolean executeScript(Resource scriptResource, String key, int maxRequests, TemporalUnit temporalUnit) {
        long expiration = temporalUnit.getDuration().getSeconds();
        Boolean result = redisOperations.execute(
                RedisScript.of(scriptResource, Boolean.class),
                List.of(key),
                Integer.toString(maxRequests),
                Long.toString(expiration)
        );
        return result != null && result;
    }
}
