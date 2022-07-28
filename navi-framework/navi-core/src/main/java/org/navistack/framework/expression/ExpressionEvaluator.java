package org.navistack.framework.expression;

public interface ExpressionEvaluator {
    <T> T evaluate(Class<T> valueType, Object... args);
}
