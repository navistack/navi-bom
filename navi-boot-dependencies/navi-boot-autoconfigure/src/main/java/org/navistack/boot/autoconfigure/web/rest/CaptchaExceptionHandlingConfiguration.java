package org.navistack.boot.autoconfigure.web.rest;

import org.navistack.framework.captcha.CaptchaTester;
import org.navistack.framework.web.rest.exceptionhandling.translators.captcha.CaptchaExceptionHandlingConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(CaptchaTester.class)
public class CaptchaExceptionHandlingConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public CaptchaExceptionHandlingConfigurer captchaExceptionHandlingConfigurer() {
        return new CaptchaExceptionHandlingConfigurer();
    }
}
