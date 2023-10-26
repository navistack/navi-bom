package org.navistack.framework.security;

import org.springframework.security.core.Authentication;

public interface TokenService {
    String issue(Authentication authentication) throws TokenServiceIssueException;

    Authentication authenticate(String token) throws TokenServiceAuthenticationException;

    boolean validate(String token);
}
