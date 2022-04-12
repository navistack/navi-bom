package org.navistack.framework.security.jwt;

import org.springframework.security.core.Authentication;

public interface JwtTokenService {
    String issue(Authentication authentication);
    Authentication authenticate(String token);
    boolean validate(String token);
}
