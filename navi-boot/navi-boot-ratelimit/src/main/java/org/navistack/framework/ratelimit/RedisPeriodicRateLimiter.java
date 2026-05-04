package org.navistack.framework.ratelimit;

import lombok.Getter;
import lombok.Setter;
import org.navistack.framework.cache.CacheScope;
import org.navistack.framework.utils.Strings;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.script.RedisScript;

import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.Collections;

@Getter
@Setter
public class RedisPeriodicRateLimiter implements PeriodicRateLimiter {
    private static final String DEFAULT_USER_KEY = "GLOBAL_RESOURCE";
    private static final Resource
            DEFAULT_SCRIPT_RESOURCE = new ClassPathResource("navi/scripts/periodicratelimiter.lua");
    private static final CacheScope
            DEFAULT_CACHE_SCOPE = CacheScope.of("NAVI").scope("PERIODIC_RATE_LIMITER");

    private final RedisOperations<String, Long> redisOperations;

    private Resource scriptResource = DEFAULT_SCRIPT_RESOURCE;
    private CacheScope cacheScope = DEFAULT_CACHE_SCOPE;
    private RedisScript<Boolean> script = RedisScript.of(DEFAULT_SCRIPT_RESOURCE, Boolean.class);

    public RedisPeriodicRateLimiter(RedisOperations<String, Long> redisOperations) {
        this.redisOperations = redisOperations;
    }

    public void setScriptResource(Resource scriptResource) {
        this.scriptResource = scriptResource;
        this.script = RedisScript.of(scriptResource, Boolean.class);
    }

    @Override
    public boolean tryAcquire(String key, int maxRequests, TemporalUnit temporalUnit) {
        key = Strings.hasText(key) ? key : DEFAULT_USER_KEY;
        long epochMilli = Instant.now().truncatedTo(temporalUnit).toEpochMilli();
        String scopedKey = cacheScope.key(key, Long.toString(epochMilli));
        long expiration = temporalUnit.getDuration().getSeconds();
        Boolean result = redisOperations.execute(
                script,
                Collections.singletonList(scopedKey),
                Integer.toString(maxRequests),
                Long.toString(expiration)
        );
        return result != null && result;
    }
}
