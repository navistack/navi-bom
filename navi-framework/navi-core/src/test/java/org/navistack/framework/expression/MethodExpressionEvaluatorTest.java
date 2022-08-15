package org.navistack.framework.expression;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

class MethodExpressionEvaluatorTest {

    @Test
    void evaluate() throws Exception {
        String expression = "MethodExpressionEvaluator.evaluate(valueType=#{#valueType.name},args=[#{#args}])";
        Method evaluate = MethodExpressionEvaluator.class.getMethod("evaluate", Class.class, Object[].class);
        MethodExpressionEvaluator evaluator = new MethodExpressionEvaluator(expression, evaluate);
        String evaluated = evaluator.evaluate(String.class, String.class, new Object[]{"arg1", "arg2"});
        String expected = "MethodExpressionEvaluator.evaluate(valueType=java.lang.String,args=[arg1,arg2])";
        assertThat(evaluated).isEqualTo(expected);
    }
}
