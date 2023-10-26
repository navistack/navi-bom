package org.navistack.framework.locking;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.navistack.framework.expression.ExpressionEvaluator;
import org.navistack.framework.expression.MethodExpressionEvaluatorFactory;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.temporal.TemporalUnit;

@Slf4j
@Aspect
public class PessimisticLockAspect {
    @Getter
    @Setter
    @NonNull
    private MethodExpressionEvaluatorFactory evaluatorFactory;

    @Getter
    @Setter
    @NonNull
    private PessimisticLockService lockService;

    public PessimisticLockAspect(MethodExpressionEvaluatorFactory evaluatorFactory,
                                 PessimisticLockService lockService) {
        this.evaluatorFactory = evaluatorFactory;
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
        ExpressionEvaluator evaluator = evaluatorFactory.getObject(userKeyExpression, method);
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
