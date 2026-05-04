package org.navistack.framework.ratelimit;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PeriodicRateLimitHandler implements RateLimitHandler<PeriodicRateLimit> {
    @NonNull
    private PeriodicRateLimiter rateLimiter;

    @Override
    public boolean tryAcquire(PeriodicRateLimit annotation, String resolvedKey) {
        return rateLimiter.tryAcquire(resolvedKey, annotation.maxRequests(), annotation.temporalUnit());
    }
}
