package org.navistack.framework.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

public interface JwtTokenResolver {
    Claims getClaims(Authentication authentication);
    Authentication getAuthentication(Claims claims);
}
