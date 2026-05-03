package org.navistack.framework.expression;

public interface ExpressionEngine {
    BoundExpression compile(String expression);

    BoundExpression compile(String expression, String[] parameterNames);
}
