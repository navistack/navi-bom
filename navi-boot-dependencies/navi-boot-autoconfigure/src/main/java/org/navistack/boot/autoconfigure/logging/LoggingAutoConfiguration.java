package org.navistack.boot.autoconfigure.logging;

import org.navistack.framework.logging.OperationLog;
import org.navistack.framework.logging.OperationLogAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingAutoConfiguration {
    @Configuration
    @ConditionalOnClass(OperationLog.class)
    public static class OperationLogConfiguration {
        @Bean
        @ConditionalOnMissingBean(OperationLogAspect.class)
        public OperationLogAspect operationLogAspect() {
            return new OperationLogAspect();
        }
    }
}
