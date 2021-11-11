package org.navistack.boot.autoconfigure.web.rest;

import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.ExceptionHandlingImpl;
import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.common.AdviceTrait;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
public class RestResultAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(AdviceTrait.class)
    public ExceptionHandlingImpl exceptionHandling() {
        return new ExceptionHandlingImpl();
    }

}
