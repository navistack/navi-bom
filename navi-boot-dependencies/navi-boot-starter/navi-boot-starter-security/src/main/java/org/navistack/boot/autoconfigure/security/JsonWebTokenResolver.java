package org.navistack.boot.autoconfigure.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

public interface JsonWebTokenResolver {
    Claims getClaims(Authentication authentication);
    Authentication getAuthentication(Claims claims);
}
