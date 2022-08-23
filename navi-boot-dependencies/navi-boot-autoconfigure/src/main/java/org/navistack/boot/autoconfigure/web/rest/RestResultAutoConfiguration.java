package org.navistack.boot.autoconfigure.web.rest;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.ExceptionHandlingImpl;
import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties(RestResultProperties.class)
public class RestResultAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ExceptionHandlerTrait.class)
    public ExceptionHandlingImpl exceptionHandling(RestResultProperties properties) {
        ExceptionHandlingImpl exceptionHandling = new ExceptionHandlingImpl();
        exceptionHandling.setIncludeStackTrace(properties.isIncludeStackTrace());
        return exceptionHandling;
    }

}
