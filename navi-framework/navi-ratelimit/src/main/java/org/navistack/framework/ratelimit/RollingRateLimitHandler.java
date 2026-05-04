package org.navistack.framework.ratelimit;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@RequiredArgsConstructor
public class RollingRateLimitHandler implements RateLimitHandler<RollingRateLimit> {
    @NonNull
    private RollingRateLimiter rateLimiter;

    @Override
    public boolean tryAcquire(RollingRateLimit annotation, String resolvedKey) {
        return rateLimiter.tryAcquire(resolvedKey, annotation.maxRequests(),
                Duration.of(annotation.windowSize(), annotation.windowSizeUnit()));
    }
}
