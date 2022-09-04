package org.navistack.boot.autoconfigure.security;

import org.navistack.boot.autoconfigure.security.jwt.JwtTokenConfiguration;
import org.navistack.framework.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@AutoConfiguration
@ConditionalOnClass(WebSecurityConfigurer.class)
@ConditionalOnBean(WebSecurityConfiguration.class)
@AutoConfigureAfter(SecurityAutoConfiguration.class)
@Import(JwtTokenConfiguration.class)
public class TokenAutoConfiguration {
    private ApplicationContext applicationContext;

    @Bean
    @ConditionalOnMissingBean
    public TokenConfigurer tokenConfigurer() {
        TokenFilter tokenFilter = getBeanOrNull(applicationContext, TokenFilter.class);
        TokenAuthenticationProvider tokenAuthenticationProvider = getBeanOrNull(applicationContext, TokenAuthenticationProvider.class);
        if (tokenAuthenticationProvider == null) {
            TokenService tokenService = getBeanOrNull(applicationContext, TokenService.class);
            if (tokenService != null) {
                tokenAuthenticationProvider = new TokenAuthenticationProvider(tokenService);
            }
        }
        TokenConfigurer configurer = new TokenConfigurer();
        configurer.setTokenFilter(tokenFilter);
        configurer.setTokenAuthenticationProvider(tokenAuthenticationProvider);
        return configurer;
    }

    @Bean
    @ConditionalOnBean(TokenConfigurer.class)
    public TokenHttpSecurityBeanPostProcessor tokenHttpSecurityBeanPostProcessor(TokenConfigurer tokenConfigurer) {
        return new TokenHttpSecurityBeanPostProcessor(tokenConfigurer);
    }

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private static <T> T getBeanOrNull(ApplicationContext context, Class<T> type) {
        String[] beanNames = context.getBeanNamesForType(type);
        if (beanNames.length != 1) {
            return null;
        }
        return context.getBean(beanNames[0], type);
    }
}
