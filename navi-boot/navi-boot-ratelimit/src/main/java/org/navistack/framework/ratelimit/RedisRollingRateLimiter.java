package org.navistack.framework.ratelimit;

import lombok.Getter;
import lombok.Setter;
import org.navistack.framework.cache.CacheScope;
import org.navistack.framework.utils.Strings;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.script.RedisScript;

import java.time.Duration;
import java.util.Collections;

@Getter
@Setter
public class RedisRollingRateLimiter implements RollingRateLimiter {
    private static final String DEFAULT_USER_KEY = "GLOBAL_RESOURCE";
    private static final Resource
            DEFAULT_SCRIPT_RESOURCE = new ClassPathResource("navi/scripts/rollingratelimiter.lua");
    private static final CacheScope
            DEFAULT_CACHE_SCOPE = CacheScope.of("NAVI").scope("ROLLING_RATE_LIMITER");

    private final RedisOperations<String, Long> redisOperations;

    private Resource scriptResource = DEFAULT_SCRIPT_RESOURCE;
    private CacheScope cacheScope = DEFAULT_CACHE_SCOPE;
    private RedisScript<Boolean> script = RedisScript.of(DEFAULT_SCRIPT_RESOURCE, Boolean.class);

    public RedisRollingRateLimiter(RedisOperations<String, Long> redisOperations) {
        this.redisOperations = redisOperations;
    }

    public void setScriptResource(Resource scriptResource) {
        this.scriptResource = scriptResource;
        this.script = RedisScript.of(scriptResource, Boolean.class);
    }

    @Override
    public boolean tryAcquire(String key, int maxRequests, Duration windowSize) {
        key = Strings.hasText(key) ? key : DEFAULT_USER_KEY;
        String scopedKey = cacheScope.key(key);
        long windowSizeInMillis = windowSize.toMillis();
        Boolean result = redisOperations.execute(
                script,
                Collections.singletonList(scopedKey),
                Integer.toString(maxRequests),
                Long.toString(System.currentTimeMillis()),
                Long.toString(windowSizeInMillis)
        );
        return result != null && result;
    }
}
