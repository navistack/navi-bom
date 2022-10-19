package org.navistack.boot.autoconfigure.web.rest;

import org.junit.jupiter.api.Test;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionHandling;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.navistack.framework.web.rest.exceptionhandling.translators.captcha.CaptchaTestFailureExceptionTranslator;
import org.navistack.framework.web.rest.exceptionhandling.translators.core.CoreExceptionHandlingConfigurer;
import org.navistack.framework.web.rest.exceptionhandling.translators.core.error.CodedExceptionTranslator;
import org.navistack.framework.web.rest.exceptionhandling.translators.core.error.UserExceptionTranslator;
import org.navistack.framework.web.rest.exceptionhandling.translators.locking.LockAcquisitionFailureExceptionTranslator;
import org.navistack.framework.web.rest.exceptionhandling.translators.ratelimit.RateLimitExceededExceptionTranslator;
import org.navistack.framework.web.rest.exceptionhandling.translators.security.SecurityExceptionHandlingConfigurer;
import org.navistack.framework.web.rest.exceptionhandling.translators.security.access.AccessDeniedExceptionTranslator;
import org.navistack.framework.web.rest.exceptionhandling.translators.security.core.AuthenticationExceptionTranslator;
import org.navistack.framework.web.rest.exceptionhandling.translators.validation.BindExceptionTranslator;
import org.navistack.framework.web.rest.exceptionhandling.translators.validation.ValidationExceptionHandlingConfigurer;
import org.navistack.framework.web.rest.exceptionhandling.translators.web.HttpRequestMethodNotSupportedExceptionTranslator;
import org.navistack.framework.web.rest.exceptionhandling.translators.web.WebExceptionHandlingConfigurer;
import org.navistack.framework.web.rest.exceptionhandling.translators.web.bind.MissingServletRequestParameterExceptionTranslator;
import org.navistack.framework.web.rest.exceptionhandling.translators.web.servlet.NoHandlerFoundExceptionTranslator;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class ExceptionHandlingAutoConfigurationTest {
    private final WebApplicationContextRunner contextRunner = new WebApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(ExceptionHandlingAutoConfiguration.class));

    @Test
    void testDefault() {
        contextRunner.run(context -> {
            assertThat(context).hasSingleBean(ExceptionHandling.class);
            assertThat(context).hasSingleBean(CoreExceptionHandlingConfigurer.class);
            assertThat(context).hasSingleBean(ValidationExceptionHandlingConfigurer.class);
            assertThat(context).hasSingleBean(WebExceptionHandlingConfigurer.class);
            ExceptionHandling exceptionHandling = context.getBean(ExceptionHandling.class);
            assertThat(exceptionHandling).extracting(ExceptionHandling::getTranslators).isNotNull();
            Collection<ExceptionTranslator> translators = exceptionHandling.getTranslators();
            assertThat(translators).hasExactlyElementsOfTypes(
                    CaptchaTestFailureExceptionTranslator.class,
                    LockAcquisitionFailureExceptionTranslator.class,
                    RateLimitExceededExceptionTranslator.class,
                    UserExceptionTranslator.class,
                    CodedExceptionTranslator.class,
                    BindExceptionTranslator.class,
                    MissingServletRequestParameterExceptionTranslator.class,
                    NoHandlerFoundExceptionTranslator.class,
                    HttpRequestMethodNotSupportedExceptionTranslator.class
            );
        });
    }

    @Test
    void testWithProperties() {
        contextRunner.withPropertyValues(ExceptionHandlingProperties.PROPERTIES_PREFIX + ".include-stack-trace=true")
                .run(context -> {
                    assertThat(context).hasSingleBean(ExceptionHandling.class);
                    ExceptionHandling exceptionHandling = context.getBean(ExceptionHandling.class);
                    assertThat(exceptionHandling).extracting(ExceptionHandling::isIncludeStackTrace).isEqualTo(true);
                });
    }

    @Test
    void testWithAdditionalConfigurer() {
        contextRunner.withBean(SecurityExceptionHandlingConfigurer.class, SecurityExceptionHandlingConfigurer::new)
                .run(context -> {
                    assertThat(context).hasSingleBean(ExceptionHandling.class);
                    ExceptionHandling exceptionHandling = context.getBean(ExceptionHandling.class);
                    assertThat(exceptionHandling).extracting(ExceptionHandling::getTranslators).isNotNull();
                    Collection<ExceptionTranslator> translators = exceptionHandling.getTranslators();
                    assertThat(translators).hasAtLeastOneElementOfType(AccessDeniedExceptionTranslator.class);
                    assertThat(translators).hasAtLeastOneElementOfType(AuthenticationExceptionTranslator.class);
                });
    }
}
