package org.navistack.framework.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.util.ClassUtils;

@Slf4j
@RequiredArgsConstructor
public class ExceptionHandlingHttpSecurityBeanPostProcessor implements BeanPostProcessor {
    private final ObjectProvider<ExceptionHandlingHttpSecuritySupport> httpSecuritySupport;

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName)
            throws BeansException {
        if (ClassUtils.isAssignableValue(HttpSecurity.class, bean)) {
            httpSecuritySupport.ifAvailable(support -> register((HttpSecurity) bean, beanName, support));
        }
        return bean;
    }

    private void register(final HttpSecurity http,
                          final String beanName,
                          final ExceptionHandlingHttpSecuritySupport support) {
        try {
            http.exceptionHandling(configurer -> {
                configurer.authenticationEntryPoint(support)
                        .accessDeniedHandler(support);
            });
        } catch (final Exception cause) {
            throw new BeanCreationException(beanName, cause);
        }
        log.trace("Register HttpSecurity's exceptionHandling");
    }
}
