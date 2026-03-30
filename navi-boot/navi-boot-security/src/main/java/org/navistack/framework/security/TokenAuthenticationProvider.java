package org.navistack.framework.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;

public class TokenAuthenticationProvider implements AuthenticationProvider {
    private final TokenService tokenService;

    public TokenAuthenticationProvider(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = determineToken(authentication);
        try {
            if (!StringUtils.hasText(token) || !this.tokenService.validate(token)) {
                throw new InvalidTokenException("Invalid Token received");
            }
            return this.tokenService.authenticate(token);
        } catch (TokenServiceException e) {
            throw new AuthenticationServiceException("Failed to authenticate token", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.isAssignableFrom(authentication);
    }

    private String determineToken(Authentication authentication) {
        Object token = authentication.getCredentials();
        return token == null ? null : token.toString();
    }
}
