package org.navistack.framework.locking;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PessimisticLock {
    /**
     * The key to identify the lock.
     */
    String key();

    /**
     * How long should the lock be released.
     */
    long timeout();

    /**
     * Timeout unit. Default to milliseconds.
     */
    TimeUnit unit() default TimeUnit.MILLISECONDS;
}
