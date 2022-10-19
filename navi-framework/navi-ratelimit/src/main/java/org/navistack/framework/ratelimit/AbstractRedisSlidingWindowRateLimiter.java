package org.navistack.framework.ratelimit;

import lombok.Getter;
import lombok.Setter;
import org.navistack.framework.cache.CacheKeyBuilder;
import org.navistack.framework.cache.PrefixedCacheKeyBuilder;
import org.navistack.framework.utils.Strings;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.time.Duration;

@Getter
@Setter
public abstract class AbstractRedisSlidingWindowRateLimiter extends AbstractSlidingWindowRateLimiter {
    private final static String DEFAULT_USER_KEY = "GLOBAL_RESOURCE";
    private final static Resource DEFAULT_SCRIPT_RESOURCE = new ClassPathResource("scripts/slidingwindowratelimiter.lua");
    private final static CacheKeyBuilder KEY_BUILDER = new PrefixedCacheKeyBuilder(".", "NAVI", "RATE_LIMITER");

    private Resource scriptResource = DEFAULT_SCRIPT_RESOURCE;
    private CacheKeyBuilder keyBuilder = KEY_BUILDER;

    @Override
    public boolean tryAcquire(String key) {
        return tryAcquire(key, getMaxRequestsOfWindow(), getSizeOfWindow());
    }

    @Override
    public boolean tryAcquire(String key, int maxRequests, Duration windowSize) {
        key = Strings.hasText(key) ? key : DEFAULT_USER_KEY;
        return executeScript(scriptResource, keyBuilder.build(key), maxRequests, System.currentTimeMillis(), windowSize);
    }

    public void setKeyPrefix(String prefix) {
        if (!Strings.hasLength(prefix)) {
            throw new IllegalArgumentException("prefix can not be null or empty");
        }
        keyBuilder = new PrefixedCacheKeyBuilder(".", prefix);
    }

    protected abstract boolean executeScript(Resource scriptResource, String key, int maxRequests, long timestamp, Duration windowSize);
}
