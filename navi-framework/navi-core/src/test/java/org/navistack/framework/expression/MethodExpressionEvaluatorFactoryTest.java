package org.navistack.framework.expression;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

class MethodExpressionEvaluatorFactoryTest {

    @Test
    void getObject() throws Exception {
        MethodExpressionEvaluatorFactory factory = new MethodExpressionEvaluatorFactory();
        Method getObject = MethodExpressionEvaluatorFactory.class.getMethod("getObject", String.class, Method.class);
        Method evaluate = MethodExpressionEvaluator.class.getMethod("evaluate", Class.class, Object[].class);
        assertThat(factory.getObject("exp1", getObject))
                .isNotNull()
                .isSameAs(factory.getObject("exp1", getObject))
                .isNotSameAs(factory.getObject("exp1", evaluate))
                .isNotSameAs(factory.getObject("exp2", getObject));
        assertThat(factory.getObject("exp2", getObject))
                .isNotNull()
                .isSameAs(factory.getObject("exp2", getObject))
                .isNotSameAs(factory.getObject("exp2", evaluate))
                .isNotSameAs(factory.getObject("exp1", getObject));
        assertThat(factory.getObject("exp1", evaluate))
                .isNotNull()
                .isSameAs(factory.getObject("exp1", evaluate))
                .isNotSameAs(factory.getObject("exp1", getObject))
                .isNotSameAs(factory.getObject("exp2", evaluate));
        assertThat(factory.getObject("exp2", evaluate))
                .isNotNull()
                .isSameAs(factory.getObject("exp2", evaluate))
                .isNotSameAs(factory.getObject("exp2", getObject))
                .isNotSameAs(factory.getObject("exp1", evaluate));
    }
}
