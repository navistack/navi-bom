package org.navistack.framework.ratelimit;

import java.time.Duration;

public interface SlidingWindowRateLimiter extends RateLimiter {
    boolean tryAcquire(String key);

    boolean tryAcquire(String key, int maxRequests, Duration windowSize);
}
