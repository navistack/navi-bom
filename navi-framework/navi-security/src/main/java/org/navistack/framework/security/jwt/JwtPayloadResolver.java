package org.navistack.framework.security.jwt;

import org.springframework.security.core.Authentication;

public interface JwtPayloadResolver {
    JwtClaims getClaims(Authentication authentication);

    Authentication getAuthentication(JwtClaims claims);
}
