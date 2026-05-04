package org.navistack.boot.web.rest.autoconfigure;

import org.navistack.framework.ratelimit.RateLimitExceededException;
import org.navistack.framework.web.rest.exceptionhandling.translators.ratelimit.RateLimitExceptionHandlingConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(RateLimitExceededException.class)
public class RateLimitExceptionHandlingConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public RateLimitExceptionHandlingConfigurer rateLimitExceptionHandlingConfigurer() {
        return new RateLimitExceptionHandlingConfigurer();
    }
}
