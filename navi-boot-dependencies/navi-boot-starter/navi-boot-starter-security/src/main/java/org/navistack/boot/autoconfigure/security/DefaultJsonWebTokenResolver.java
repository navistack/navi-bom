package org.navistack.boot.autoconfigure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class DefaultJsonWebTokenResolver implements JsonWebTokenResolver {
    private static final String AUTHORITIES_KEY = "auth";

    @Override
    public Claims getClaims(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Claims claims = new DefaultClaims();
        claims.put(AUTHORITIES_KEY, authorities);
        return claims;
    }

    @Override
    public Authentication getAuthentication(Claims claims) {
        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .filter(auth -> !auth.trim().isEmpty())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "NO_PASSWORD", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "NO_CREDENTIALS", authorities);
    }
}