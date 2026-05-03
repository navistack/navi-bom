package org.navistack.framework.expression;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.support.StandardEvaluationContext;

class SpelBoundExpression implements BoundExpression {
    private final Expression expression;
    private final String[] parameterNames;

    SpelBoundExpression(Expression expression, String[] parameterNames) {
        this.expression = expression;
        this.parameterNames = parameterNames != null ? parameterNames : new String[0];
    }

    @Override
    public <T> T evaluate(Class<T> resultType, Object... args) {
        EvaluationContext ctx = buildEvaluationContext(args);
        return resultType.cast(expression.getValue(ctx));
    }

    private EvaluationContext buildEvaluationContext(Object[] args) {
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        for (int i = 0; i < args.length; i++) {
            ctx.setVariable("arg" + i, args[i]);
            if (i < parameterNames.length) {
                ctx.setVariable(parameterNames[i], args[i]);
            }
        }
        return ctx;
    }
}
