package org.navistack.framework.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.navistack.framework.expression.MethodExpressionEvaluator;
import org.navistack.framework.expression.MethodExpressionEvaluatorFactory;

import java.lang.reflect.Method;

@Aspect
@Slf4j
public class OperationLogAspect {
    private final MethodExpressionEvaluatorFactory evaluatorFactory = new MethodExpressionEvaluatorFactory();

    private final OperationLogService logService;

    public OperationLogAspect(OperationLogService logService) {
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
        MethodExpressionEvaluator evaluator = evaluatorFactory.getObject(messageExpression, method);
        String message = evaluator.evaluate(String.class, args);
        if (OperationLog.Level.INFO.equals(level)) {
            logService.info(declaringType, message);
            return;
        }
        switch (level) {
            case TRACE:
                logService.trace(declaringType, message);
                return;
            case DEBUG:
                logService.debug(declaringType, message);
                return;
            case INFO:
                logService.info(declaringType, message);
                return;
            case WARN:
                logService.warn(declaringType, message);
                return;
            case ERROR:
                logService.error(declaringType, message);
                return;
        }
    }
}
