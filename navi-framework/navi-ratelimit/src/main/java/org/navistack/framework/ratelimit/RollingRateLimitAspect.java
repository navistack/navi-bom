package org.navistack.framework.ratelimit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.navistack.framework.expression.MethodExpressionBinder;

@Aspect
public class RollingRateLimitAspect extends AbstractRateLimitAspect<RollingRateLimit> {
    public RollingRateLimitAspect(MethodExpressionBinder expressionBinder,
                                  RateLimitHandler<RollingRateLimit> handler) {
        super(expressionBinder, handler);
    }

    @Around("@annotation(rollingRateLimit)")
    public Object around(ProceedingJoinPoint joinPoint, RollingRateLimit rollingRateLimit) throws Throwable {
        return handle(joinPoint, rollingRateLimit, rollingRateLimit.key(), rollingRateLimit.message());
    }
}
