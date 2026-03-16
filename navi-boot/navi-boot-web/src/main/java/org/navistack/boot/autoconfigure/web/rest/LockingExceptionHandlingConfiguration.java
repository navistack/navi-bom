package org.navistack.boot.autoconfigure.web.rest;

import org.navistack.framework.locking.PessimisticLockService;
import org.navistack.framework.web.rest.exceptionhandling.translators.locking.LockingExceptionHandlingConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(PessimisticLockService.class)
public class LockingExceptionHandlingConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public LockingExceptionHandlingConfigurer lockingExceptionHandlingConfigurer() {
        return new LockingExceptionHandlingConfigurer();
    }
}
