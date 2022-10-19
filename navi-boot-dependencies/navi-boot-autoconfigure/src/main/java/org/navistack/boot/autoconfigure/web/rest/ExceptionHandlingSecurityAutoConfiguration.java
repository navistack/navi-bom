package org.navistack.boot.autoconfigure.web.rest;

import org.navistack.framework.web.rest.ExceptionHandlingHttpSecurityBeanPostProcessor;
import org.navistack.framework.web.rest.ExceptionHandlingHttpSecuritySupport;
import org.navistack.framework.web.rest.exceptionhandling.translators.security.SecurityExceptionHandlingConfigurer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
@ConditionalOnClass(WebSecurityConfigurer.class)
@ConditionalOnBean(WebSecurityConfiguration.class)
@AutoConfigureBefore(ExceptionHandlingAutoConfiguration.class)
@AutoConfigureAfter(SecurityAutoConfiguration.class)
public class ExceptionHandlingSecurityAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SecurityExceptionHandlingConfigurer securityExceptionHandlingConfigurer() {
        return new SecurityExceptionHandlingConfigurer();
    }

    @Bean
    @ConditionalOnMissingBean
    public ExceptionHandlingHttpSecuritySupport exceptionHandlingHttpSecuritySupport(
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver handlerExceptionResolver) {
        return new ExceptionHandlingHttpSecuritySupport(handlerExceptionResolver);
    }

    @Bean
    @ConditionalOnMissingBean
    public ExceptionHandlingHttpSecurityBeanPostProcessor exceptionHandlingHttpSecurityBeanPostProcessor(final ObjectProvider<ExceptionHandlingHttpSecuritySupport> securityRestResultSupport) {
        return new ExceptionHandlingHttpSecurityBeanPostProcessor(securityRestResultSupport);
    }
}
