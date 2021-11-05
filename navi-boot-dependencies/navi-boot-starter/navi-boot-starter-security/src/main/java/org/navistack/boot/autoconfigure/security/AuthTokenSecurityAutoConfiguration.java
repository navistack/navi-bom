package org.navistack.boot.autoconfigure.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;

@Configuration
@ConditionalOnClass(WebSecurityConfigurer.class)
@ConditionalOnBean(TokenService.class)
public class AuthTokenSecurityAutoConfiguration {
    @Bean
    public AuthTokenConfigurer authTokenConfigurer(TokenService tokenService) {
        return new AuthTokenConfigurer(tokenService);
    }
}
