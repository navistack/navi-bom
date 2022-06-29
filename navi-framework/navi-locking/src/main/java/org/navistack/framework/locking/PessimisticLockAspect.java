package org.navistack.framework.locking;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.navistack.framework.cache.KvCacheService;
import org.navistack.framework.core.problem.PlatformProblems;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
public class PessimisticLockAspect {
    private final Map<Method, AnnotatedExpressionEvaluator<?>> evaluatorCache = new ConcurrentHashMap<>();

    @Getter
    @Setter
    private String lockKeyPrefix = "P_LOCK.";

    private final KvCacheService cacheService;

    public PessimisticLockAspect(KvCacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Pointcut("@annotation(PessimisticLock)")
    public void pointcut() {

    }

    @Around("pointcut()")
    @SuppressWarnings("unchecked")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        PessimisticLock pessimisticLock = method.getAnnotation(PessimisticLock.class);
        String userKeyExpression = pessimisticLock.key();
        long timeout = pessimisticLock.timeout();
        TimeUnit unit = pessimisticLock.unit();

        AnnotatedExpressionEvaluator<String> evaluator = (AnnotatedExpressionEvaluator<String>)
                evaluatorCache.computeIfAbsent(
                        method,
                        k -> new AnnotatedExpressionEvaluator<>(userKeyExpression, method, String.class)
                );

        String userKey = evaluator.evaluate(args);
        String key = lockKeyPrefix + userKey;
        if (!cacheService.setIfAbsent(key, "1", timeout, unit)) {
            throw PlatformProblems.resourceLocked("Resource Locked");
        }

        try {
            return joinPoint.proceed(args);
        } finally {
            cacheService.delete(key);
        }
    }
}
