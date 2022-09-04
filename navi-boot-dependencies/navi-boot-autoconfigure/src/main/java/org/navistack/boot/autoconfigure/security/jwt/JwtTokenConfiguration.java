package org.navistack.boot.autoconfigure.security.jwt;

import org.navistack.framework.security.TokenService;
import org.navistack.framework.security.jwt.DefaultJwtTokenResolver;
import org.navistack.framework.security.jwt.JwtTokenResolver;
import org.navistack.framework.security.jwt.JwtTokenService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;

@Configuration
@ConditionalOnClass(WebSecurityConfigurer.class)
@EnableConfigurationProperties(JwtTokenProperties.class)
public class JwtTokenConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public JwtTokenResolver jwtTokenResolver() {
        return new DefaultJwtTokenResolver();
    }

    @Bean
    @ConditionalOnMissingBean
    public TokenService tokenService(JwtTokenProperties properties, JwtTokenResolver tokenResolver) {
        JwtTokenService jsonWebTokenService = new JwtTokenService(properties.getSecret(), properties.getValidity());
        jsonWebTokenService.setTokenResolver(tokenResolver);
        return jsonWebTokenService;
    }
}
