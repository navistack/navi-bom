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

    @Around("@annotation(annotation)")
    public Object around(ProceedingJoinPoint joinPoint, RollingRateLimit annotation) throws Throwable {
        return handle(joinPoint, annotation, annotation.key(), annotation.message());
    }
}
