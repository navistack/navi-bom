package org.navistack.boot.autoconfigure.logging;

import org.navistack.framework.logging.OperationLog;
import org.navistack.framework.logging.OperationLogAspect;
import org.navistack.framework.logging.OperationLogService;
import org.navistack.framework.logging.Slf4jOperationLogService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
    public static class OperationLogConfiguration {
        @Bean
        @ConditionalOnMissingBean(OperationLogAspect.class)
        public OperationLogAspect operationLogAspect(OperationLogService logService) {
            return new OperationLogAspect(logService);
        }
    }
}
