package org.navistack.boot.autoconfigure.web.rest;

import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.security.RestResultHttpSecurityBeanPostProcessor;
import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.security.SecurityExceptionHandling;
import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.common.AdviceTrait;
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
@AutoConfigureBefore(RestResultAutoConfiguration.class)
@AutoConfigureAfter(SecurityAutoConfiguration.class)
public class RestResultSecurityAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(AdviceTrait.class)
    public SecurityExceptionHandling securityExceptionHandling() {
        return new SecurityExceptionHandling();
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