package org.navistack.boot.autoconfigure.web.rest;

import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.common.AdviceTrait;
import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.security.SecurityExceptionHandling;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
@ConditionalOnClass(WebSecurityConfigurer.class)
@Order(Ordered.LOWEST_PRECEDENCE - 21)
@AutoConfigureBefore({SecurityAutoConfiguration.class, RestResultAutoConfiguration.class})
public class RestResultSecurityAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(AdviceTrait.class)
    public AdviceTrait securityExceptionHandling() {
        return new SecurityExceptionHandling();
    }

    @Bean
    public SecurityFilterChain restResultSecurityFilterChain(HttpSecurity http, SecurityRestResultSupport support) throws Exception {
        return http.exceptionHandling()
                .authenticationEntryPoint(support)
                .accessDeniedHandler(support)
                .and()
                .build();
    }

    @Bean
    public SecurityRestResultSupport securityProblemSupport(
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver handlerExceptionResolver) {
        return new SecurityRestResultSupport(handlerExceptionResolver);
    }

}
