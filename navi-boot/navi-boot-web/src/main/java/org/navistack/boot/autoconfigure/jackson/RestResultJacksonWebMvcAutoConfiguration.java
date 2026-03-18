package org.navistack.boot.autoconfigure.jackson;

import org.navistack.framework.jackson.web.RestResultModule;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.webmvc.autoconfigure.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverters;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tools.jackson.databind.json.JsonMapper;

@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
@ConditionalOnBean(WebMvcConfigurationSupport.class)
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
public class RestResultJacksonWebMvcAutoConfiguration implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(HttpMessageConverters.ServerBuilder builder) {
        final JsonMapper mapper = JsonMapper.builder()
                .addModule(new RestResultModule())
                .build();
        builder.addCustomConverter(new JacksonJsonHttpMessageConverter(mapper));
    }

}
