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

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Collections;

@Getter
@Setter
public class RedisFixedWindowRateLimiter implements FixedWindowRateLimiter {
    private static final String DEFAULT_USER_KEY = "GLOBAL_RESOURCE";
    private static final Resource DEFAULT_SCRIPT_RESOURCE = new ClassPathResource("navi/scripts/fixedwindowratelimiter.lua");
    private static final CacheKeyBuilder KEY_BUILDER = new PrefixedCacheKeyBuilder(".", "NAVI", "FIXED_WINDOW_RATE_LIMITER");
    private static final int DEFAULT_MAX_REQUESTS = 1000;
    private static final TemporalUnit DEFAULT_TEMPORAL_UNIT = ChronoUnit.MINUTES;

    private final RedisOperations<String, Long> redisOperations;

    private Resource scriptResource = DEFAULT_SCRIPT_RESOURCE;
    private CacheKeyBuilder keyBuilder = KEY_BUILDER;
    private int maxRequests = DEFAULT_MAX_REQUESTS;
    private TemporalUnit temporalUnit = DEFAULT_TEMPORAL_UNIT;

    public RedisFixedWindowRateLimiter(RedisOperations<String, Long> redisOperations) {
        this.redisOperations = redisOperations;
    }

    @Override
    public boolean tryAcquire(String key) {
        return tryAcquire(key, getMaxRequests(), getTemporalUnit());
    }

    @Override
    public boolean tryAcquire(String key, int maxRequests, TemporalUnit temporalUnit) {
        key = Strings.hasText(key) ? key : DEFAULT_USER_KEY;
        long epochMilli = Instant.now().truncatedTo(getTemporalUnit()).toEpochMilli();
        return executeScript(scriptResource, keyBuilder.build(key, Long.toString(epochMilli)), maxRequests, temporalUnit);
    }

    public void setKeyPrefix(String prefix) {
        if (!Strings.hasLength(prefix)) {
            throw new IllegalArgumentException("prefix can not be null or empty");
        }
        keyBuilder = new PrefixedCacheKeyBuilder(".", prefix);
    }

    private boolean executeScript(Resource scriptResource, String key, int maxRequests, TemporalUnit temporalUnit) {
        long expiration = temporalUnit.getDuration().getSeconds();
        Boolean result = redisOperations.execute(
                RedisScript.of(scriptResource, Boolean.class),
                Collections.singletonList(key),
                Integer.toString(maxRequests),
                Long.toString(expiration)
        );
        return result != null && result;
    }
}
