package org.navistack.framework.expression;

import java.lang.reflect.Method;

public interface MethodExpressionBinder extends ExpressionBinder {
    BoundExpression bind(String expression, Method method);
}
