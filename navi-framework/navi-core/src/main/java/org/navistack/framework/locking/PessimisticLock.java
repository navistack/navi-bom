package org.navistack.framework.locking;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PessimisticLock {
    /**
     * The key to identify the tryLock.
     */
    String key();

    /**
     * How long should the tryLock be released.
     */
    long timeout();

    /**
     * Timeout unit. Default to milliseconds.
     */
    ChronoUnit unit() default ChronoUnit.MILLIS;
}
