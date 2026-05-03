package org.navistack.framework.expression;

import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import static org.assertj.core.api.Assertions.assertThat;

class SpelBoundExpressionTest {
    private static final SpelExpressionParser PARSER = new SpelExpressionParser();
    private static final TemplateParserContext TEMPLATE_CTX = new TemplateParserContext();

    private SpelBoundExpression compile(String template, String... paramNames) {
        Expression expression = PARSER.parseExpression(template, TEMPLATE_CTX);
        return new SpelBoundExpression(expression, paramNames);
    }

    @Test
    void shouldEvaluateStaticTemplateWithNoArgs() {
        SpelBoundExpression bound = compile("hello-world");
        assertThat(bound.evaluate(String.class)).isEqualTo("hello-world");
    }

    @Test
    void shouldBindArgsByPositionalIndex() {
        SpelBoundExpression bound = compile("#{#arg0}.#{#arg1}", new String[0]);
        String result = bound.evaluate(String.class, "foo", "bar");
        assertThat(result).isEqualTo("foo.bar");
    }

    @Test
    void shouldBindArgsByParameterNameWhenNamesAreProvided() {
        SpelBoundExpression bound = compile("#{#userId}.#{#action}", "userId", "action");
        String result = bound.evaluate(String.class, "u42", "read");
        assertThat(result).isEqualTo("u42.read");
    }

    @Test
    void shouldSupportBothPositionalAndNamedBindingSimultaneously() {
        SpelBoundExpression bound = compile("#{#arg0}:#{#name}", "name");
        String result = bound.evaluate(String.class, "positional");
        assertThat(result).isEqualTo("positional:positional");
    }

    @Test
    void shouldEvaluateToRequestedType() {
        SpelBoundExpression bound = compile("#{#arg0 + #arg1}", new String[0]);
        Integer result = bound.evaluate(Integer.class, 3, 4);
        assertThat(result).isEqualTo(7);
    }

    @Test
    void shouldTolerateNullParameterNamesArray() {
        SpelBoundExpression bound = compile("#{#arg0}", (String[]) null);
        String result = bound.evaluate(String.class, "ok");
        assertThat(result).isEqualTo("ok");
    }
}
