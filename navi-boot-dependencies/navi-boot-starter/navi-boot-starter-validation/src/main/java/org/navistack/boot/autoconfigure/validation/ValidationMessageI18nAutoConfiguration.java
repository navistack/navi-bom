package org.navistack.boot.autoconfigure.validation;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.executable.ExecutableValidator;

@Configuration
@ConditionalOnClass(ExecutableValidator.class)
@AutoConfigureBefore(ValidationAutoConfiguration.class)
public class ValidationMessageI18nAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        MessageInterpolatorFactory interpolatorFactory = new MessageInterpolatorFactory();
        factoryBean.setMessageInterpolator(interpolatorFactory.getObject());
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/validation/messages");
        messageSource.setDefaultEncoding("UTF-8");
        factoryBean.setValidationMessageSource(messageSource);
        return factoryBean;
    }
}
