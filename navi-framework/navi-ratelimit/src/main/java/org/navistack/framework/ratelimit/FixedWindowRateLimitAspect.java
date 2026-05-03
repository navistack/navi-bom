package org.navistack.framework.ratelimit;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.navistack.framework.expression.BoundExpression;
import org.navistack.framework.expression.MethodExpressionBinder;

import java.lang.reflect.Method;
import java.time.temporal.TemporalUnit;

@Aspect
public class FixedWindowRateLimitAspect {
    @Getter
    @Setter
    @NonNull
    private MethodExpressionBinder expressionBinder;

    @Getter
    @Setter
    @NonNull
    private FixedWindowRateLimiter rateLimiter;

    public FixedWindowRateLimitAspect(MethodExpressionBinder expressionBinder,
                                      FixedWindowRateLimiter rateLimiter) {
        this.expressionBinder = expressionBinder;
        this.rateLimiter = rateLimiter;
    }

    @Around("@annotation(rateLimit)")
    public Object around(ProceedingJoinPoint joinPoint, FixedWindowRateLimit rateLimit) throws Throwable {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String userKeyExpression = rateLimit.key();
        int maxRequests = rateLimit.maxRequests();
        TemporalUnit timeUnit = rateLimit.temporalUnit();
        String message = rateLimit.message();
        BoundExpression expression = expressionBinder.bind(userKeyExpression, method);
        String userKey = expression.evaluate(String.class, args);
        if (!rateLimiter.tryAcquire(userKey, maxRequests, timeUnit)) {
            throw new RateLimitExceededException(message);
        }

        return joinPoint.proceed();
    }
}
