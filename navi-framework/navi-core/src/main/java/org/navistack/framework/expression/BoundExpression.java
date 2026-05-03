package org.navistack.framework.expression;

public interface BoundExpression {
    <T> T evaluate(Class<T> resultType, Object... args);
}
