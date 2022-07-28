package org.navistack.framework.ratelimit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.temporal.ChronoUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FixedWindowRateLimit {
    String key();

    int maxRequests();

    ChronoUnit temporalUnit() default ChronoUnit.SECONDS;
}
