package org.navistack.framework.expression;

import java.lang.reflect.Method;

public interface MethodExpressionEvaluatorFactory {
    ExpressionEvaluator getObject(String expression, Method method);
}
