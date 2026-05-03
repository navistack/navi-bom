package org.navistack.framework.expression;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpelExpressionEngine implements ExpressionEngine {
    private static final ExpressionParser DEFAULT_PARSER = new SpelExpressionParser();
    private static final ParserContext DEFAULT_PARSER_CONTEXT = new TemplateParserContext();

    private final ExpressionParser parser;
    private final ParserContext parserContext;

    public SpelExpressionEngine() {
        this(DEFAULT_PARSER, DEFAULT_PARSER_CONTEXT);
    }

    public SpelExpressionEngine(ExpressionParser parser, ParserContext parserContext) {
        this.parser = parser;
        this.parserContext = parserContext;
    }

    @Override
    public BoundExpression compile(String expression) {
        Expression compiled = parser.parseExpression(expression, parserContext);
        return new SpelBoundExpression(compiled, new String[0]);
    }

    @Override
    public BoundExpression compile(String expression, String[] parameterNames) {
        Expression compiled = parser.parseExpression(expression, parserContext);
        return new SpelBoundExpression(compiled, parameterNames);
    }
}
