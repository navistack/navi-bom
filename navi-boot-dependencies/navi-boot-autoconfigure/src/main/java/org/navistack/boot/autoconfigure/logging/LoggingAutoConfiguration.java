package org.navistack.boot.autoconfigure.logging;

import lombok.Getter;
import lombok.Setter;
import org.navistack.framework.expression.DefaultMethodExpressionEvaluatorFactory;
import org.navistack.framework.expression.MethodExpressionEvaluatorFactory;
import org.navistack.framework.logging.OperationLog;
import org.navistack.framework.logging.OperationLogAspect;
import org.navistack.framework.logging.OperationLogService;
import org.navistack.framework.logging.Slf4jOperationLogService;
import org.navistack.framework.utils.ApplicationContexts;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public OperationLogService operationLogService() {
        return new Slf4jOperationLogService();
    }

    @Configuration
    @ConditionalOnClass(OperationLog.class)
    public static class OperationLogConfiguration implements ApplicationContextAware {
        @Getter
        @Setter
        private ApplicationContext applicationContext;

        @Bean
        @ConditionalOnMissingBean(OperationLogAspect.class)
        public OperationLogAspect operationLogAspect(OperationLogService logService) {
            MethodExpressionEvaluatorFactory evaluatorFactory = ApplicationContexts.getBean(applicationContext, MethodExpressionEvaluatorFactory.class);
            if (evaluatorFactory == null) {
                evaluatorFactory = new DefaultMethodExpressionEvaluatorFactory();
            }
            return new OperationLogAspect(evaluatorFactory, logService);
        }
    }
}
