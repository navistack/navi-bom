package org.navistack.framework.ratelimit;

import java.time.temporal.TemporalUnit;

public interface PeriodicRateLimiter {
    boolean tryAcquire(String key, int maxRequests, TemporalUnit temporalUnit);
}
