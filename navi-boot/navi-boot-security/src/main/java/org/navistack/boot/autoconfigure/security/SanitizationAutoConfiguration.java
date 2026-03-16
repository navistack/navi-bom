package org.navistack.boot.autoconfigure.security;

import org.navistack.framework.security.AntiSamyContentSanitizer;
import org.navistack.framework.security.ContentSanitizer;
import org.navistack.framework.security.SanitizationFilter;
import org.navistack.framework.security.SanitizingHttpServletRequestWrapperBuilder;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@AutoConfiguration
@ConditionalOnWebApplication
@ConditionalOnClass(ContentSanitizer.class)
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties(SanitizationProperties.class)
public class SanitizationAutoConfiguration implements ResourceLoaderAware {
    private SanitizationProperties properties;
    private ResourceLoader resourceLoader;

    @Bean
    @ConditionalOnMissingBean
    public ContentSanitizer contentSanitizer() throws Exception {
        AntiSamyProperties antiSamyProperties = properties.getAntiSamy();
        String policyLocation = antiSamyProperties.getPolicyLocation();
        Resource policyResource = resourceLoader.getResource(policyLocation);
        Policy policy = Policy.getInstance(policyResource.getInputStream());
        AntiSamy antiSamy = new AntiSamy(policy);

        AntiSamyContentSanitizer sanitizer = new AntiSamyContentSanitizer(antiSamy);
        sanitizer.setReturnContentAsIsOnError(properties.isReturnContentAsIsOnError());
        sanitizer.setDefaultContentOnError(properties.getDefaultContentOnError());
        return sanitizer;
    }

    @Bean
    public FilterRegistrationBean<SanitizationFilter> sanitizationFilterRegistrationBean(
            ContentSanitizer contentSanitizer
    ) {
        SanitizingHttpServletRequestWrapperBuilder wrapperBuilder =
                new SanitizingHttpServletRequestWrapperBuilder()
                        .contentSanitizer(contentSanitizer)
                        .bypassedHeaders(properties.getBypassedHeaders())
                        .bypassedParameters(properties.getBypassedParameters());

        SanitizationFilter sanitizationFilter = new SanitizationFilter(wrapperBuilder);

        FilterRegistrationBean<SanitizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
        registrationBean.setUrlPatterns(properties.getUrlPatterns());
        registrationBean.setFilter(sanitizationFilter);
        return registrationBean;
    }

    @Autowired
    public void setProperties(SanitizationProperties properties) {
        this.properties = properties;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
