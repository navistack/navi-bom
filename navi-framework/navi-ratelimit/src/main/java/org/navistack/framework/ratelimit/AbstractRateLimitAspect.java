package org.navistack.framework.ratelimit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.navistack.framework.expression.BoundExpression;
import org.navistack.framework.expression.MethodExpressionBinder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractRateLimitAspect<A extends Annotation> {
    private final MethodExpressionBinder expressionBinder;
    private final RateLimitHandler<A> handler;
    private final ConcurrentHashMap<Method, MethodBinding<A>> cache = new ConcurrentHashMap<>();

    protected AbstractRateLimitAspect(MethodExpressionBinder expressionBinder,
                                      RateLimitHandler<A> handler) {
        this.expressionBinder = expressionBinder;
        this.handler = handler;
    }

    protected Object handle(ProceedingJoinPoint joinPoint, A annotation,
                            String keyExpr, String message) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        MethodBinding<A> binding = cache.computeIfAbsent(method,
                m -> new MethodBinding<>(annotation, expressionBinder.bind(keyExpr, m), message));

        String resolvedKey = binding.key().evaluate(String.class, joinPoint.getArgs());
        if (!handler.tryAcquire(binding.annotation(), resolvedKey)) {
            throw new RateLimitExceededException(binding.message());
        }

        return joinPoint.proceed();
    }

    private record MethodBinding<A>(A annotation, BoundExpression key, String message) {}
}
