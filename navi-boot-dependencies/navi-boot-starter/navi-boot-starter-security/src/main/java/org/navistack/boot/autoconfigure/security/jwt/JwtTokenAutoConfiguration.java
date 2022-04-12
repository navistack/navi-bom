package org.navistack.boot.autoconfigure.security.jwt;

import org.navistack.framework.security.jwt.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.util.Assert;

@Configuration
@ConditionalOnClass(WebSecurityConfigurer.class)
@EnableConfigurationProperties(JwtTokenProperties.class)
public class JwtTokenAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public JwtTokenResolver jwtTokenResolver() {
        return new DefaultJwtTokenResolver();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtTokenService tokenService(
            JwtTokenProperties properties,
            JwtTokenResolver tokenResolver
    ) {
        JwtTokenProperties.Token token = properties.getToken();
        String secret = token.getSecret();
        Assert.hasText(secret, "secret can not be null or empty");
        int validity = token.getValidity();
        Assert.isTrue(validity > 0, "Validity can not be negative or zero");

        DefaultJwtTokenService jsonWebTokenService = new DefaultJwtTokenService(secret, validity);
        jsonWebTokenService.setTokenResolver(tokenResolver);
        return jsonWebTokenService;
    }

    @Bean
    public JwtTokenConfigurer authTokenConfigurer(JwtTokenService jwtTokenService) {
        return new JwtTokenConfigurer(jwtTokenService);
    }
}
