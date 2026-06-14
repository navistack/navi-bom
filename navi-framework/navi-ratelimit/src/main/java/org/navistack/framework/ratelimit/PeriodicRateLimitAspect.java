package org.navistack.framework.ratelimit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.navistack.framework.expression.MethodExpressionBinder;

@Aspect
public class PeriodicRateLimitAspect extends AbstractRateLimitAspect<PeriodicRateLimit> {
    public PeriodicRateLimitAspect(MethodExpressionBinder expressionBinder,
                                   RateLimitHandler<PeriodicRateLimit> handler) {
        super(expressionBinder, handler);
    }

    @Around("@annotation(periodicRateLimit)")
    public Object around(ProceedingJoinPoint joinPoint, PeriodicRateLimit periodicRateLimit) throws Throwable {
        return handle(joinPoint, periodicRateLimit, periodicRateLimit.key(), periodicRateLimit.message());
    }
}
