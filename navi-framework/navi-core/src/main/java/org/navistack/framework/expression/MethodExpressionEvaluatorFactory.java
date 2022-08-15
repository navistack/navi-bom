package org.navistack.framework.expression;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.navistack.framework.utils.Asserts;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MethodExpressionEvaluatorFactory {
    private final Map<MethodExpressionPair, MethodExpressionEvaluator> evaluatorCache = new ConcurrentHashMap<>();

    public MethodExpressionEvaluator getObject(String expression, Method method) {
        Asserts.notNull(expression, "expression must not be null");
        Asserts.notNull(method, "method must not be null");
        return evaluatorCache.computeIfAbsent(MethodExpressionPair.of(expression, method), p -> new MethodExpressionEvaluator(expression, method));
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class MethodExpressionPair {
        private String expression;
        private Method method;
    }
}
