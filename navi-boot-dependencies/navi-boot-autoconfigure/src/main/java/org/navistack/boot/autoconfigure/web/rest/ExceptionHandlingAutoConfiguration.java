package org.navistack.boot.autoconfigure.web.rest;

import lombok.Setter;
import org.navistack.framework.utils.ApplicationContexts;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionHandling;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionHandlingBuilder;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionHandlingConfigurer;
import org.navistack.framework.web.rest.exceptionhandling.translators.core.CoreExceptionHandlingConfigurer;
import org.navistack.framework.web.rest.exceptionhandling.translators.validation.ValidationExceptionHandlingConfigurer;
import org.navistack.framework.web.rest.exceptionhandling.translators.web.WebExceptionHandlingConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Map;

@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties(ExceptionHandlingProperties.class)
@Import({
        CaptchaExceptionHandlingConfiguration.class,
        LockingExceptionHandlingConfiguration.class,
        RateLimitExceptionHandlingConfiguration.class,
})
public class ExceptionHandlingAutoConfiguration implements ApplicationContextAware {
    @Setter
    private ApplicationContext applicationContext;

    @Bean
    @ConditionalOnMissingBean
    public ExceptionHandling exceptionHandling(ExceptionHandlingProperties properties) {
        ExceptionHandlingBuilder builder = new ExceptionHandlingBuilder();
        builder.setIncludeStackTrace(properties.isIncludeStackTrace());

        MessageSource messageSource = ApplicationContexts.getBean(applicationContext, MessageSource.class);
        if (messageSource != null) {
            builder.setMessageSource(messageSource);
        }

        LocaleResolver localeResolver = ApplicationContexts.getBean(applicationContext, LocaleResolver.class);
        if (localeResolver != null) {
            builder.setLocaleResolver(localeResolver);
        }

        Map<String, ExceptionHandlingConfigurer> configurers = applicationContext.getBeansOfType(ExceptionHandlingConfigurer.class);
        for (ExceptionHandlingConfigurer configurer : configurers.values()) {
            builder.applyConfigurer(configurer);
        }

        return builder.build();
    }

    @Bean
    @ConditionalOnMissingBean
    public CoreExceptionHandlingConfigurer coreExceptionHandlingConfigurer() {
        return new CoreExceptionHandlingConfigurer();
    }

    @Bean
    @ConditionalOnMissingBean
    public ValidationExceptionHandlingConfigurer validationExceptionHandlingConfigurer() {
        return new ValidationExceptionHandlingConfigurer();
    }

    @Bean
    @ConditionalOnMissingBean
    public WebExceptionHandlingConfigurer webExceptionHandlingConfigurer() {
        return new WebExceptionHandlingConfigurer();
    }
}
