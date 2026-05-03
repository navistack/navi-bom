package org.navistack.framework.expression;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class GenericMethodExpressionBinder implements MethodExpressionBinder {
    private final ExpressionEngine engine;
    private final ParameterNameDiscoverer parameterNameDiscoverer;
    private final Map<String, BoundExpression> expressionCache = new ConcurrentHashMap<>();
    private final Map<MethodExpressionKey, BoundExpression> methodCache = new ConcurrentHashMap<>();

    public GenericMethodExpressionBinder(ExpressionEngine engine, ParameterNameDiscoverer parameterNameDiscoverer) {
        this.engine = Objects.requireNonNull(engine, "engine must not be null");
        this.parameterNameDiscoverer = Objects.requireNonNull(
                parameterNameDiscoverer,
                "parameterNameDiscoverer must not be null"
        );
    }

    public GenericMethodExpressionBinder(ExpressionEngine engine) {
        this(engine, new ReflectionParameterNameDiscoverer());
    }

    @Override
    public BoundExpression bind(String expression) {
        Objects.requireNonNull(expression, "expression must not be null");
        return expressionCache.computeIfAbsent(expression, engine::compile);
    }

    @Override
    public BoundExpression bind(String expression, Method method) {
        Objects.requireNonNull(expression, "expression must not be null");
        Objects.requireNonNull(method, "method must not be null");
        return methodCache.computeIfAbsent(
                new MethodExpressionKey(expression, method),
                k -> engine.compile(expression, parameterNameDiscoverer.getParameterNames(method)));
    }

    private record MethodExpressionKey(String expression, Method method) {
    }
}
