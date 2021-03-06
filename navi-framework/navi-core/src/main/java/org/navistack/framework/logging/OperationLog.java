package org.navistack.framework.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {
    String value();

    Level level() default Level.INFO;

    enum Level {
        TRACE,
        DEBUG,
        INFO,
        WARN,
        ERROR
    }
}
