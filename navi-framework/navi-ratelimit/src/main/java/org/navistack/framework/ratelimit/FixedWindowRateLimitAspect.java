package org.navistack.framework.ratelimit;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.navistack.framework.expression.ExpressionEvaluator;
import org.navistack.framework.expression.MethodExpressionEvaluatorFactory;

import java.lang.reflect.Method;
import java.time.temporal.TemporalUnit;

@Aspect
public class FixedWindowRateLimitAspect {
    @Getter
    @Setter
    @NonNull
    private MethodExpressionEvaluatorFactory evaluatorFactory;
    @Getter
    @Setter
    @NonNull
    private FixedWindowRateLimiter rateLimiter;

    public FixedWindowRateLimitAspect(MethodExpressionEvaluatorFactory evaluatorFactory, FixedWindowRateLimiter rateLimiter) {
        this.evaluatorFactory = evaluatorFactory;
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
        ExpressionEvaluator evaluator = evaluatorFactory.getObject(userKeyExpression, method);
        String userKey = evaluator.evaluate(String.class, args);
        if (!rateLimiter.tryAcquire(userKey, maxRequests, timeUnit)) {
            throw new RateLimitExceededException(message);
        }

        return joinPoint.proceed();
    }
}
