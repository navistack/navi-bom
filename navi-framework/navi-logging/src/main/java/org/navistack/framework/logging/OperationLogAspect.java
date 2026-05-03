package org.navistack.framework.logging;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.navistack.framework.expression.BoundExpression;
import org.navistack.framework.expression.MethodExpressionBinder;

import java.lang.reflect.Method;

@Aspect
@Slf4j
public class OperationLogAspect {
    @Getter
    @Setter
    @NonNull
    private MethodExpressionBinder expressionBinder;

    @Getter
    @Setter
    @NonNull
    private OperationLogService logService;

    public OperationLogAspect(MethodExpressionBinder expressionBinder, OperationLogService logService) {
        this.expressionBinder = expressionBinder;
        this.logService = logService;
    }

    @Before(value = "@annotation(operationLog)")
    public void before(JoinPoint joinPoint, OperationLog operationLog) {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> declaringType = signature.getDeclaringType();
        String messageExpression = operationLog.value();
        OperationLog.Level level = operationLog.level();
        BoundExpression expression = expressionBinder.bind(messageExpression, method);
        String message = expression.evaluate(String.class, args);
        if (OperationLog.Level.INFO.equals(level)) {
            logService.info(declaringType, message);
            return;
        }
        switch (level) {
            case TRACE -> logService.trace(declaringType, message);
            case DEBUG -> logService.debug(declaringType, message);
            case WARN -> logService.warn(declaringType, message);
            case ERROR -> logService.error(declaringType, message);
            default -> logService.info(declaringType, message);
        }
    }
}
