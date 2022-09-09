package org.navistack.boot.autoconfigure.web.rest;

import org.navistack.framework.web.rest.RestResultHttpSecurityBeanPostProcessor;
import org.navistack.framework.web.rest.SecurityRestResultSupport;
import org.navistack.framework.web.rest.exceptionhanders.SecurityExceptionHandlingImpl;
import org.navistack.framework.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Optional;

@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
@ConditionalOnClass(WebSecurityConfigurer.class)
@ConditionalOnBean(WebSecurityConfiguration.class)
@AutoConfigureBefore(RestResultAutoConfiguration.class)
@AutoConfigureAfter(SecurityAutoConfiguration.class)
public class RestResultSecurityAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(ExceptionHandlerTrait.class)
    public SecurityExceptionHandlingImpl securityExceptionHandling(
            RestResultProperties properties,
            Optional<MessageSource> messageSource,
            Optional<LocaleResolver> localeResolver
    ) {
        SecurityExceptionHandlingImpl securityExceptionHandling = new SecurityExceptionHandlingImpl();
        securityExceptionHandling.setIncludeStackTrace(properties.isIncludeStackTrace());
        messageSource.ifPresent(securityExceptionHandling::setMessageSource);
        localeResolver.ifPresent(securityExceptionHandling::setLocaleResolver);
        return securityExceptionHandling;
    }

    @Bean
    public SecurityRestResultSupport securityProblemSupport(
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver handlerExceptionResolver) {
        return new SecurityRestResultSupport(handlerExceptionResolver);
    }

    @Bean
    public RestResultHttpSecurityBeanPostProcessor problemSecurityBeanPostProcessor(final ObjectProvider<SecurityRestResultSupport> securityRestResultSupport) {
        return new RestResultHttpSecurityBeanPostProcessor(securityRestResultSupport);
    }
}
