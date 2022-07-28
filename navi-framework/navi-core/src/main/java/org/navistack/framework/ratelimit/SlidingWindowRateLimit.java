package org.navistack.framework.ratelimit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.temporal.ChronoUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SlidingWindowRateLimit {
    String key();

    int maxRequests();

    long windowSize();

    ChronoUnit sizeUnit() default ChronoUnit.MILLIS;
}
