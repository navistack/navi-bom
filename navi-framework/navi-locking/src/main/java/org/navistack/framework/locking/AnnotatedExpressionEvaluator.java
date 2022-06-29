package org.navistack.framework.locking;

import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

public class AnnotatedExpressionEvaluator<T> {
    private static final SpelExpressionParser SPEL_EXPRESSION_PARSER = new SpelExpressionParser();
    private static final TemplateParserContext TEMPLATE_PARSER_CONTEXT = new TemplateParserContext();
    private static final ParameterNameDiscoverer PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    private final Expression expression;
    private final String[] parameterNames;
    private final Class<T> valueType;

    public AnnotatedExpressionEvaluator(String expression, Method method, Class<T> valueType) {
        this.expression = SPEL_EXPRESSION_PARSER.parseExpression(expression, TEMPLATE_PARSER_CONTEXT);
        this.parameterNames = PARAMETER_NAME_DISCOVERER.getParameterNames(method);
        this.valueType = valueType;
    }

    public T evaluate(Object[] args) {
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
