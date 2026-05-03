package org.navistack.framework.expression;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GenericMethodExpressionBinderTest {
    private static ExpressionEngine stubEngine() {
        return new ExpressionEngine() {
            @Override
            public BoundExpression compile(String expression) {
                return new BoundExpression() {
                    @Override
                    public <T> T evaluate(Class<T> resultType, Object... args) {
                        return resultType.cast(expression + ":" + args.length);
                    }
                };
            }

            @Override
            public BoundExpression compile(String expression, String[] parameterNames) {
                return new BoundExpression() {
                    @Override
                    public <T> T evaluate(Class<T> resultType, Object... args) {
                        return resultType.cast(expression + ":" + args.length + ":" + parameterNames.length);
                    }
                };
            }
        };
    }

    @Test
    void shouldCacheExpressionOnlyBindingsPerExpressionString() {
        GenericMethodExpressionBinder binder = new GenericMethodExpressionBinder(stubEngine());
        BoundExpression first = binder.bind("expr1");
        assertThat(binder.bind("expr1"))
                .isSameAs(first);
        assertThat(binder.bind("expr2"))
                .isNotSameAs(first);
    }

    @Test
    void shouldCacheMethodBindingsPerExpressionAndMethod() throws Exception {
        GenericMethodExpressionBinder binder = new GenericMethodExpressionBinder(stubEngine());
        Method m1 = SampleMethods.class
                .getDeclaredMethod("twoParams", String.class, String.class);
        Method m2 = Object.class.getMethod("toString");

        BoundExpression e1m1 = binder.bind("expr1", m1);
        assertThat(binder.bind("expr1", m1)).isSameAs(e1m1);
        assertThat(binder.bind("expr2", m1)).isNotSameAs(e1m1);
        assertThat(binder.bind("expr1", m2)).isNotSameAs(e1m1);
    }

    @Test
    void shouldKeepExpressionOnlyAndMethodCachesIndependent() throws Exception {
        GenericMethodExpressionBinder binder = new GenericMethodExpressionBinder(stubEngine());
        Method m = SampleMethods.class
                .getDeclaredMethod("twoParams", String.class, String.class);
        assertThat(binder.bind("expr"))
                .isNotSameAs(binder.bind("expr", m));
    }

    @Test
    void shouldPassParameterNamesToEngineWhenBindingWithMethod() throws Exception {
        GenericMethodExpressionBinder binder = new GenericMethodExpressionBinder(stubEngine());
        Method m = SampleMethods.class
                .getDeclaredMethod("twoParams", String.class, String.class);
        BoundExpression bound = binder.bind("e", m);
        String result = bound.evaluate(String.class, "x", "y");
        assertThat(result).isEqualTo("e:2:2");
    }

    @Test
    void shouldRejectNullExpression() {
        GenericMethodExpressionBinder binder = new GenericMethodExpressionBinder(stubEngine());
        assertThatThrownBy(() -> binder.bind(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void shouldRejectNullMethod() {
        GenericMethodExpressionBinder binder = new GenericMethodExpressionBinder(stubEngine());
        assertThatThrownBy(() -> binder.bind("expr", null))
                .isInstanceOf(NullPointerException.class);
    }

    @SuppressWarnings("unused")
    static class SampleMethods {
        static String twoParams(String a, String b) {
            return a + b;
        }
    }
}
