package org.navistack.framework.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.util.ClassUtils;

@Slf4j
@RequiredArgsConstructor
public class TokenHttpSecurityBeanPostProcessor implements BeanPostProcessor {
    private final TokenConfigurer tokenConfigurer;

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        if (ClassUtils.isAssignableValue(HttpSecurity.class, bean)) {
            register((HttpSecurity) bean, beanName, tokenConfigurer);
        }
        return bean;
    }

    private void register(final HttpSecurity http, final String beanName, final TokenConfigurer configurer) {
        try {
            http.apply(configurer);
        } catch (final Exception cause) {
            throw new BeanCreationException(beanName, cause);
        }
        log.info("Configure HttpSecurity with TokenConfigurer");
    }
}
