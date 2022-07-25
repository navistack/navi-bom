package org.navistack.framework.security.jwt;

import java.time.Instant;
import java.util.Map;

public interface JwtClaims extends Map<String, Object> {
    String getIssuer();

    void putIssuer(String issuer);

    String getSubject();

    void putSubject(String subject);

    String getAudience();

    void putAudience(String audience);

    Instant getExpiration();

    void putExpiration(Instant expiration);

    Instant getNotBefore();

    void putNotBefore(Instant notBefore);

    Instant getIssuedAt();

    void putIssuedAt(Instant issuedAt);

    String getId();

    void putId(String id);

    <T> T get(String key, Class<T> targetType);
}
