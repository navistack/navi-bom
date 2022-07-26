package org.navistack.framework.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

@Aspect
@Slf4j
public class OperationLogAspect {
    private final SpelExpressionParser SPEL_EXPRESSION_PARSER = new SpelExpressionParser();
    private final TemplateParserContext TEMPLATE_PARSER_CONTEXT = new TemplateParserContext();
    private final ParameterNameDiscoverer PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    @Pointcut("@annotation(OperationLog)")
    public void pointcut() {

    }

    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Class<?> declaringType = signature.getDeclaringType();
        Method method = signature.getMethod();
        OperationLog operationLog = method.getAnnotation(OperationLog.class);

        String message = evaluateExpression(operationLog.value(), buildEvaluationContext(method, args));
        Logger logger = LoggerFactory.getLogger(declaringType);
        logger.info(message);
    }

    private String evaluateExpression(String expressionLiteral, EvaluationContext evaluationContext) {
        try {
            Expression expression = SPEL_EXPRESSION_PARSER.parseExpression(expressionLiteral, TEMPLATE_PARSER_CONTEXT);
            return (String) expression.getValue(evaluationContext);
        } catch (Exception e) {
            log.error("Failed to parse and/or evaluate expression: {}", expressionLiteral, e);
            return expressionLiteral;
        }
    }

    private EvaluationContext buildEvaluationContext(Method method, Object[] args) {
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        String[] params = PARAMETER_NAME_DISCOVERER.getParameterNames(method);
        for (int i = 0; i < args.length; ++i) {
            evaluationContext.setVariable("arg" + i, args[i]);
            if (params != null && i < params.length) {
                evaluationContext.setVariable(params[i], args[i]);
            }
        }
        return evaluationContext;
    }
}
