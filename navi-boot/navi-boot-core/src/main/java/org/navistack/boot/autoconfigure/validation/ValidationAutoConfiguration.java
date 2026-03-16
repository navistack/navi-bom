package org.navistack.boot.autoconfigure.validation;

import jakarta.validation.executable.ExecutableValidator;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ConditionalOnClass(ExecutableValidator.class)
@AutoConfigureBefore(org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration.class)
public class ValidationAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public LocalValidatorFactoryBean getValidator(MessageSource messageSource) {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        MessageInterpolatorFactory interpolatorFactory = new MessageInterpolatorFactory();
        factoryBean.setMessageInterpolator(interpolatorFactory.getObject());
        factoryBean.setValidationMessageSource(messageSource);
        return factoryBean;
    }
}
