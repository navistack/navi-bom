package org.navistack.boot.autoconfigure.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.navistack.framework.jackson.web.RestErrResultJsonSerializer;
import org.navistack.framework.jackson.web.RestOkResultJsonSerializer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
@ConditionalOnBean(WebMvcConfigurationSupport.class)
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
public class RestResultJacksonWebMvcAutoConfiguration implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
        final ObjectMapper mapper = Jackson2ObjectMapperBuilder.json()
                .serializers(
                        new RestOkResultJsonSerializer(),
                        new RestErrResultJsonSerializer()
                )
                .build();

        converters.add(new MappingJackson2HttpMessageConverter(mapper));
    }

}
