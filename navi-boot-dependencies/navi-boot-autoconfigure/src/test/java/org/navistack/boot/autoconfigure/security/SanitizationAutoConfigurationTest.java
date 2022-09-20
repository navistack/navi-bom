package org.navistack.boot.autoconfigure.security;

import org.junit.jupiter.api.Test;
import org.navistack.framework.security.AntiSamyContentSanitizer;
import org.navistack.framework.security.ContentSanitizer;
import org.navistack.framework.security.SanitizationFilter;
import org.navistack.framework.security.SanitizingHttpServletRequestWrapperBuilder;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import static org.assertj.core.api.Assertions.assertThat;

class SanitizationAutoConfigurationTest {

    @Test
    void testDefault() {
        new WebApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(SanitizationAutoConfiguration.class))
                .run(context -> {
                    assertThat(context).hasSingleBean(ContentSanitizer.class);
                    assertThat(context).hasBean("sanitizationFilterRegistrationBean");
                });
    }

    @Test
    void testWithProperties() {
        new WebApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(SanitizationAutoConfiguration.class))
                .withPropertyValues(SanitizationProperties.PROPERTIES_PREFIX + ".url-patterns=/post/**,/comment/**")
                .withPropertyValues(SanitizationProperties.PROPERTIES_PREFIX + ".bypassed-headers=Authorization")
                .withPropertyValues(SanitizationProperties.PROPERTIES_PREFIX + ".bypassed-parameters=token")
                .withPropertyValues(SanitizationProperties.PROPERTIES_PREFIX + ".return-content-as-is-on-error=false")
                .withPropertyValues(SanitizationProperties.PROPERTIES_PREFIX + ".default-content-on-error=default-content-on-error")
                .withPropertyValues(SanitizationProperties.PROPERTIES_PREFIX + ".anti-samy.policy-location=classpath:antisamy-tinymce.xml")
                .run(context -> {
                    assertThat(context).hasSingleBean(ContentSanitizer.class);
                    ContentSanitizer sanitizer = context.getBean(ContentSanitizer.class);
                    assertThat(sanitizer).isInstanceOf(AntiSamyContentSanitizer.class);
                    AntiSamyContentSanitizer antiSamySanitizer = (AntiSamyContentSanitizer) sanitizer;
                    assertThat(antiSamySanitizer).extracting(AntiSamyContentSanitizer::getDefaultContentOnError).isEqualTo("default-content-on-error");
                    assertThat(antiSamySanitizer).extracting(AntiSamyContentSanitizer::isReturnContentAsIsOnError).isEqualTo(false);


                    assertThat(context).hasBean("sanitizationFilterRegistrationBean");
                    FilterRegistrationBean<SanitizationFilter> registrationBean = (FilterRegistrationBean<SanitizationFilter>) context.getBean("sanitizationFilterRegistrationBean");
                    assertThat(registrationBean.getOrder()).isEqualTo(FilterRegistrationBean.HIGHEST_PRECEDENCE);
                    assertThat(registrationBean.getUrlPatterns()).containsOnly("/post/**", "/comment/**");
                    assertThat(registrationBean.getFilter()).isInstanceOf(SanitizationFilter.class);

                    SanitizationFilter filter = registrationBean.getFilter();
                    assertThat(filter).extracting(SanitizationFilter::getHttpServletRequestWrapperBuilder).isNotNull();
                    assertThat(filter).extracting(SanitizationFilter::getHttpServletRequestWrapperBuilder).isInstanceOf(SanitizingHttpServletRequestWrapperBuilder.class);

                    SanitizingHttpServletRequestWrapperBuilder wrapperBuilder = (SanitizingHttpServletRequestWrapperBuilder) filter.getHttpServletRequestWrapperBuilder();
                    assertThat(wrapperBuilder.bypassedHeaders()).containsOnly("Authorization");
                    assertThat(wrapperBuilder.bypassedParameters()).containsOnly("token");
                });
    }
}
