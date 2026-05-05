package org.navistack.framework.ratelimit;

import java.time.Duration;

public interface PeriodicRateLimiter {
    boolean tryAcquire(String key, int maxRequests, Duration period);
}
