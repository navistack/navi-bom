package org.navistack.boot.autoconfigure.web.rest;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
@ConditionalOnMissingBean(RestResultJacksonWebMvcAutoConfiguration.class)
public class RestResultJacksonAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(RestResultModule.class)
    public RestResultModule restResultModule() {
        return new RestResultModule();
    }

}
