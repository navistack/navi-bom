package org.navistack.boot.core.autoconfigure;

import org.navistack.framework.expression.ExpressionBinder;
import org.navistack.framework.expression.ExpressionEngine;
import org.navistack.framework.expression.GenericMethodExpressionBinder;
import org.navistack.framework.expression.MethodExpressionBinder;
import org.navistack.framework.expression.ParameterNameDiscoverer;
import org.navistack.framework.expression.SpelExpressionEngine;
import org.navistack.framework.expression.SpringParameterNameDiscoverer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class ExpressionAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(ExpressionEngine.class)
    public SpelExpressionEngine expressionEngine() {
        return new SpelExpressionEngine();
    }

    @Bean
    @ConditionalOnMissingBean(ParameterNameDiscoverer.class)
    public SpringParameterNameDiscoverer parameterNameDiscoverer() {
        return new SpringParameterNameDiscoverer();
    }

    @Bean
    @ConditionalOnMissingBean({ExpressionBinder.class, MethodExpressionBinder.class})
    public GenericMethodExpressionBinder methodExpressionBinder(ExpressionEngine engine,
                                                                ParameterNameDiscoverer nameDiscoverer) {
        return new GenericMethodExpressionBinder(engine, nameDiscoverer);
    }
}
