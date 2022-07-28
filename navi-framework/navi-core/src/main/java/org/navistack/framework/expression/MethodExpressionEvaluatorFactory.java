package org.navistack.framework.expression;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MethodExpressionEvaluatorFactory {
    private final Map<Method, MethodExpressionEvaluator> evaluatorCache = new ConcurrentHashMap<>();

    public MethodExpressionEvaluator getObject(String expression, Method method) {
        return evaluatorCache.computeIfAbsent(method, m -> new MethodExpressionEvaluator(expression, method));
    }
}
