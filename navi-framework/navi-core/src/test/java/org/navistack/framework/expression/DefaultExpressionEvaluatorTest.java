package org.navistack.framework.expression;

import org.junit.jupiter.api.Test;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultExpressionEvaluatorTest {

    @Test
    void evaluate() throws Exception {
        String expressionLiteral = "DefaultExpressionEvaluator.evaluate(valueType=#{#valueType.name},args=[#{#args}])";
        Method method = DefaultExpressionEvaluator.class.getMethod("evaluate", Class.class, Object[].class);

        Expression expression = new SpelExpressionParser().parseExpression(expressionLiteral, new TemplateParserContext());
        String[] parameterNames = new DefaultParameterNameDiscoverer().getParameterNames(method);
        DefaultExpressionEvaluator evaluator = new DefaultExpressionEvaluator(expression, parameterNames);

        String evaluated = evaluator.evaluate(String.class, String.class, new Object[]{"arg1", "arg2"});
        String expected = "DefaultExpressionEvaluator.evaluate(valueType=java.lang.String,args=[arg1,arg2])";
        assertThat(evaluated).isEqualTo(expected);
    }
}
