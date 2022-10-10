package org.navistack.framework.expression;

import lombok.Getter;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class DefaultExpressionEvaluator implements ExpressionEvaluator {
    @Getter
    private final Expression expression;

    @Getter
    private final String[] parameterNames;

    public DefaultExpressionEvaluator(Expression expression, String[] parameterNames) {
        if (expression == null) {
            throw new NullPointerException("expression must not be null");
        }
        this.expression = expression;
        this.parameterNames = parameterNames;
    }

    public DefaultExpressionEvaluator(Expression expression) {
        this(expression, new String[]{});
    }

    @Override
    public <T> T evaluate(Class<T> valueType, Object... args) {
        Object value = expression.getValue(buildEvaluationContext(args));
        return valueType.cast(value);
    }

    private EvaluationContext buildEvaluationContext(Object[] args) {
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        for (int i = 0; i < args.length; ++i) {
            evaluationContext.setVariable("arg" + i, args[i]);
            if (parameterNames != null && i < parameterNames.length) {
                evaluationContext.setVariable(parameterNames[i], args[i]);
            }
        }
        return evaluationContext;
    }
}
