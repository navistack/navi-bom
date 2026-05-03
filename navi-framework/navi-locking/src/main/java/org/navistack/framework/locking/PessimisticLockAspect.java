package org.navistack.framework.locking;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.navistack.framework.expression.BoundExpression;
import org.navistack.framework.expression.MethodExpressionBinder;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.temporal.TemporalUnit;

@Slf4j
@Aspect
public class PessimisticLockAspect {
    @Getter
    @Setter
    @NonNull
    private MethodExpressionBinder expressionBinder;

    @Getter
    @Setter
    @NonNull
    private PessimisticLockService lockService;

    public PessimisticLockAspect(MethodExpressionBinder expressionBinder,
                                 PessimisticLockService lockService) {
        this.expressionBinder = expressionBinder;
        this.lockService = lockService;
    }

    @Around("@annotation(pessimisticLock)")
    public Object around(ProceedingJoinPoint joinPoint, PessimisticLock pessimisticLock)
            throws Throwable {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String userKeyExpression = pessimisticLock.key();
        long timeout = pessimisticLock.timeout();
        TemporalUnit unit = pessimisticLock.unit();
        String message = pessimisticLock.message();
        BoundExpression expression = expressionBinder.bind(userKeyExpression, method);
        String userKey = expression.evaluate(String.class, args);
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
