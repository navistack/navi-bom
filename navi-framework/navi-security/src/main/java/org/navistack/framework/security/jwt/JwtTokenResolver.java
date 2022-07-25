package org.navistack.framework.security.jwt;

import org.springframework.security.core.Authentication;

public interface JwtTokenResolver {
    JwtClaims getClaims(Authentication authentication);
    Authentication getAuthentication(JwtClaims claims);
}
