package org.navistack.framework.ratelimit;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
public abstract class AbstractSlidingWindowRateLimiter implements SlidingWindowRateLimiter {
    private static final int DEFAULT_MAX_REQUESTS_OF_WINDOW = 1000;
    private static final int DEFAULT_SIZE_OF_WINDOW = 1000 /* ms */;

    private int maxRequestsOfWindow = DEFAULT_MAX_REQUESTS_OF_WINDOW;
    private Duration sizeOfWindow = Duration.of(DEFAULT_SIZE_OF_WINDOW, ChronoUnit.MILLIS);
}
