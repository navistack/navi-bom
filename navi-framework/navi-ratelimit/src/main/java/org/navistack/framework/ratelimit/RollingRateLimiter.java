package org.navistack.framework.ratelimit;

import java.time.Duration;

public interface RollingRateLimiter {
    boolean tryAcquire(String key, int maxRequests, Duration windowSize);
}
