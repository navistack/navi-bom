package org.navistack.framework.ratelimit;

import java.lang.annotation.Annotation;

public interface RateLimitHandler<A extends Annotation> {
    boolean tryAcquire(A annotation, String resolvedKey);
}
