package org.navistack.boot.autoconfigure.web.rest;

import org.navistack.framework.web.rest.exceptionhanders.ExceptionHandlingImpl;
import org.navistack.framework.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
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
    @ConditionalOnMissingBean(ExceptionHandlerTrait.class)
    public ExceptionHandlingImpl exceptionHandling() {
        return new ExceptionHandlingImpl();
    }

}
