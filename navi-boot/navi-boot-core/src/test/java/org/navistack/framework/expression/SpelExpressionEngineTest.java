package org.navistack.framework.expression;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpelExpressionEngineTest {
    private final SpelExpressionEngine engine = new SpelExpressionEngine();

    @Test
    void shouldCompileExpressionOnlyAndEvaluateWithPositionalArgs() {
        BoundExpression bound = engine.compile("prefix.#{#arg0}");
        String result = bound.evaluate(String.class, "value");
        assertThat(result).isEqualTo("prefix.value");
    }

    @Test
    void shouldCompileExpressionWithParamNamesAndEvaluateByName() {
        BoundExpression bound = engine.compile("#{#remoteAddr}", new String[]{"remoteAddr"});
        String result = bound.evaluate(String.class, "127.0.0.1");
        assertThat(result).isEqualTo("127.0.0.1");
    }

    @Test
    void shouldReturnDistinctBoundExpressionsForEachCompileCall() {
        BoundExpression first = engine.compile("#{#arg0}");
        BoundExpression second = engine.compile("#{#arg0}");
        assertThat(first).isNotSameAs(second);
    }

    @Test
    void shouldProduceBoundExpressionThatSupportsMultipleEvaluations() {
        BoundExpression bound = engine.compile("key.#{#arg0}");
        assertThat(bound.evaluate(String.class, "a")).isEqualTo("key.a");
        assertThat(bound.evaluate(String.class, "b")).isEqualTo("key.b");
    }

    @Test
    void shouldEvaluateComplexSpelTemplateWithMethodArgs() {
        BoundExpression bound = engine.compile(
                "SpelExpressionEngine.compile(expression=#{#expression},parameterNames=[#{#parameterNames}])",
                new String[]{"expression", "parameterNames"});
        String result = bound.evaluate(String.class, "myExpr", new String[]{"p1", "p2"});
        assertThat(result).isEqualTo(
                "SpelExpressionEngine.compile(expression=myExpr,parameterNames=[p1,p2])");
    }
}
