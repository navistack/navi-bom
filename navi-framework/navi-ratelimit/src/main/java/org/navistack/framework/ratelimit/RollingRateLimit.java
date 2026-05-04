package org.navistack.framework.ratelimit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.temporal.ChronoUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RollingRateLimit {
    String key();

    int maxRequests();

    long windowSize();

    ChronoUnit windowSizeUnit() default ChronoUnit.MILLIS;

    String message() default "navi.service.ratelimit.message";
}
