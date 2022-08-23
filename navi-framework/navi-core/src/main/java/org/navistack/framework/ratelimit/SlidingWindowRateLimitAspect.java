package org.navistack.framework.ratelimit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.navistack.framework.expression.MethodExpressionEvaluator;
import org.navistack.framework.expression.MethodExpressionEvaluatorFactory;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.temporal.TemporalUnit;

@Aspect
public class SlidingWindowRateLimitAspect {
    private final MethodExpressionEvaluatorFactory evaluatorFactory  = new MethodExpressionEvaluatorFactory();
    private final SlidingWindowRateLimiter rateLimiter;

    public SlidingWindowRateLimitAspect(SlidingWindowRateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Around("@annotation(rateLimit)")
    public Object around(ProceedingJoinPoint joinPoint, SlidingWindowRateLimit rateLimit) throws Throwable {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String userKeyExpression = rateLimit.key();
        int maxRequests = rateLimit.maxRequests();
        long windowSize = rateLimit.windowSize();
        TemporalUnit sizeUnit = rateLimit.sizeUnit();
        MethodExpressionEvaluator evaluator = evaluatorFactory.getObject(userKeyExpression, method);
        String userKey = evaluator.evaluate(String.class, args);
        if (!rateLimiter.tryAcquire(userKey, maxRequests, Duration.of(windowSize, sizeUnit))) {
            throw new RateLimitExceededException("Too many requests");
        }

        return joinPoint.proceed();
    }
}
