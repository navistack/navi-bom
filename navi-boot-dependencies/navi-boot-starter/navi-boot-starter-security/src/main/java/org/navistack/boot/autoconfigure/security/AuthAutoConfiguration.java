package org.navistack.boot.autoconfigure.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import java.util.Optional;

@Configuration
@EnableConfigurationProperties(AuthProperties.class)
public class AuthAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(TokenService.class)
    public TokenService tokenService(
            AuthProperties properties,
            Optional<JsonWebTokenResolver> delegator
    ) {
        AuthProperties.Token token = properties.getToken();
        String secret = token.getSecret();
        Assert.hasText(secret, "secret can not be null or empty");
        int validity = token.getValidity();
        Assert.isTrue(validity > 0, "Validity can not be negative or zero");

        JsonWebTokenService jsonWebTokenService = new JsonWebTokenService(secret, validity);
        delegator.ifPresent(jsonWebTokenService::setResolver);
        return jsonWebTokenService;
    }
}
