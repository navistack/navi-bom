package org.navistack.boot.logging.autoconfigure;

import org.navistack.framework.expression.MethodExpressionBinder;
import org.navistack.framework.logging.OperationLog;
import org.navistack.framework.logging.OperationLogAspect;
import org.navistack.framework.logging.OperationLogService;
import org.navistack.framework.logging.Slf4jOperationLogService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(OperationLog.class)
public class OperationLogConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public OperationLogService operationLogService() {
        return new Slf4jOperationLogService();
    }

    @Bean
    @ConditionalOnMissingBean(OperationLogAspect.class)
    public OperationLogAspect operationLogAspect(MethodExpressionBinder expressionBinder,
                                                 OperationLogService logService) {
        return new OperationLogAspect(expressionBinder, logService);
    }
}
