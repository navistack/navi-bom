package org.navistack.framework.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * JSON Web Token Generator
 */
@Slf4j
public class DefaultJwtTokenService implements JwtTokenService {
    private final static int DEFAULT_VALIDITY = 2 * 60 * 60 * 1000;

    private JwtTokenResolver tokenResolver;

    /**
     * Key used to sign token
     */
    private final Key key;

    /**
     * validity in milliseconds
     */
    private final int validity;

    private final JwtParser jwtParser;

    public DefaultJwtTokenService(String base64encodedKey) {
        this(base64encodedKey, DEFAULT_VALIDITY);
    }

    public DefaultJwtTokenService(byte[] keyInBytes) {
        this(keyInBytes, DEFAULT_VALIDITY);
    }

    public DefaultJwtTokenService(Key key) {
        this(key, DEFAULT_VALIDITY);
    }

    public DefaultJwtTokenService(String base64encodedKey, int validity) {
        this(
                Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64encodedKey)),
                validity
        );
    }

    public DefaultJwtTokenService(byte[] keyInBytes, int validity) {
        this(
                Keys.hmacShaKeyFor(keyInBytes),
                validity
        );
    }

    public DefaultJwtTokenService(Key key, int validity) {
        this.key = key;
        this.validity = validity;
        this.jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
    }

    public JwtTokenResolver getTokenResolver() {
        return tokenResolver;
    }

    public void setTokenResolver(JwtTokenResolver tokenResolver) {
        this.tokenResolver = tokenResolver;
    }

    @Override
    public String issue(Authentication authentication) {
        Map<String, Object> claims = tokenResolver.getClaims(authentication);

        long now = new Date().getTime();
        long expiringAt = now + validity;
        Date expiration = new Date(expiringAt);

        return Jwts.builder()
                .addClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(expiration)
                .compact();
    }

    @Override
    public Authentication authenticate(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();

        return tokenResolver.getAuthentication(claims);
    }

    @Override
    public boolean validate(String token) {
        try {
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid token: {}", token);
            log.trace("Invalid token: {}", token, e);
        }
        return false;
    }
}
