package org.navistack.framework.locking;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.navistack.framework.expression.MethodExpressionEvaluator;
import org.navistack.framework.expression.MethodExpressionEvaluatorFactory;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.temporal.TemporalUnit;

@Slf4j
@Aspect
public class PessimisticLockAspect {
    private final MethodExpressionEvaluatorFactory evaluatorFactory = new MethodExpressionEvaluatorFactory();
    private final PessimisticLockService lockService;

    public PessimisticLockAspect(PessimisticLockService lockService) {
        this.lockService = lockService;
    }

    @Around("@annotation(pessimisticLock)")
    public Object around(ProceedingJoinPoint joinPoint, PessimisticLock pessimisticLock) throws Throwable {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String userKeyExpression = pessimisticLock.key();
        long timeout = pessimisticLock.timeout();
        TemporalUnit unit = pessimisticLock.unit();
        String message = pessimisticLock.message();
        MethodExpressionEvaluator evaluator = evaluatorFactory.getObject(userKeyExpression, method);
        String userKey = evaluator.evaluate(String.class, args);
        if (!lockService.tryLock(userKey, Duration.of(timeout, unit))) {
            throw new LockAcquisitionFailureException(message);
        }
        try {
            return joinPoint.proceed(args);
        } finally {
            lockService.unlock(userKey);
        }
    }
}
