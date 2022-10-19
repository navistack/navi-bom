package org.navistack.framework.ratelimit;

import java.time.temporal.TemporalUnit;

public interface FixedWindowRateLimiter extends RateLimiter {
    boolean tryAcquire(String key);

    boolean tryAcquire(String key, int maxRequests, TemporalUnit temporalUnit);
}
