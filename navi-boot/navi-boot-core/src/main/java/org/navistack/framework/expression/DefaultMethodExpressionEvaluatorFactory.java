package org.navistack.framework.expression;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultMethodExpressionEvaluatorFactory implements MethodExpressionEvaluatorFactory {
    private static final ExpressionParser DEFAULT_EXPRESSION_PARSER = new SpelExpressionParser();
    private static final ParserContext DEFAULT_PARSER_CONTEXT = new TemplateParserContext();
    private static final ParameterNameDiscoverer
            DEFAULT_PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    private final Map<MethodExpressionPair, DefaultExpressionEvaluator>
            evaluatorCache = new ConcurrentHashMap<>();

    @Getter
    @Setter
    @NonNull
    private ExpressionParser expressionParser = DEFAULT_EXPRESSION_PARSER;

    @Getter
    @Setter
    @NonNull
    private ParserContext parserContext = DEFAULT_PARSER_CONTEXT;

    @Getter
    @Setter
    @NonNull
    private ParameterNameDiscoverer parameterNameDiscoverer = DEFAULT_PARAMETER_NAME_DISCOVERER;

    @Override
    public ExpressionEvaluator getObject(String expression, Method method) {
        if (expression == null) {
            throw new NullPointerException("expression must not be null");
        }
        if (method == null) {
            throw new NullPointerException("method must not be null");
        }
        return evaluatorCache.computeIfAbsent(
                MethodExpressionPair.of(expression, method),
                p -> constructEvaluator(expression, method)
        );
    }

    private DefaultExpressionEvaluator constructEvaluator(String expressionLiteral, Method method) {
        Expression expression = expressionParser.parseExpression(expressionLiteral, parserContext);
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
        return new DefaultExpressionEvaluator(expression, parameterNames);
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class MethodExpressionPair {
        private String expression;
        private Method method;
    }
}
