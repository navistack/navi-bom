package org.navistack.framework.ratelimit;

public interface RateLimiter {
    boolean tryAcquire(String key);
}
