package org.navistack.framework.ratelimit;

import lombok.Getter;
import lombok.Setter;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@Getter
@Setter
public abstract class AbstractFixedWindowRateLimiter implements FixedWindowRateLimiter {
    private static final int DEFAULT_MAX_REQUESTS = 1000;
    private static final TemporalUnit DEFAULT_TEMPORAL_UNIT = ChronoUnit.MINUTES;

    private int maxRequests = DEFAULT_MAX_REQUESTS;
    private TemporalUnit temporalUnit = DEFAULT_TEMPORAL_UNIT;
}
