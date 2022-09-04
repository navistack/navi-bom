package org.navistack.framework.security.jwt;

import org.navistack.framework.security.TokenAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class DefaultJwtTokenResolver implements JwtTokenResolver {
    private static final String AUTHORITIES_KEY = "auth";

    @Override
    public JwtClaims getClaims(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        JwtClaims claims = new DefaultJwtClaims();
        claims.put(AUTHORITIES_KEY, authorities);
        return claims;
    }

    @Override
    public Authentication getAuthentication(JwtClaims claims) {
        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .filter(auth -> !auth.trim().isEmpty())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "NO_PASSWORD", authorities);

        return TokenAuthentication.authenticated(principal, "NO_CREDENTIALS", authorities);
    }
}
