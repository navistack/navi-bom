package org.navistack.framework.security.jwt;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DefaultJwtClaims extends HashMap<String, Object> implements JwtClaims {
    public DefaultJwtClaims() {
        super();
    }

    public DefaultJwtClaims(Map<String, Object> claimsMap) {
        super(claimsMap);
    }

    public String getIssuer() {
        return get(JwtClaimNames.ISSUER, String.class);
    }

    public void putIssuer(String issuer) {
        put(JwtClaimNames.ISSUER, issuer);
    }

    public String getSubject() {
        return get(JwtClaimNames.SUBJECT, String.class);
    }

    public void putSubject(String subject) {
        put(JwtClaimNames.SUBJECT, subject);
    }

    public String getAudience() {
        return get(JwtClaimNames.AUDIENCE, String.class);
    }

    public void putAudience(String audience) {
        put(JwtClaimNames.AUDIENCE, audience);
    }

    public Instant getExpiration() {
        return get(JwtClaimNames.EXPIRATION, Instant.class);
    }

    public void putExpiration(Instant expiration) {
        put(JwtClaimNames.EXPIRATION, expiration);
    }

    public Instant getNotBefore() {
        return get(JwtClaimNames.NOT_BEFORE, Instant.class);
    }

    public void putNotBefore(Instant notBefore) {
        put(JwtClaimNames.NOT_BEFORE, notBefore);
    }

    public Instant getIssuedAt() {
        return get(JwtClaimNames.ISSUED_AT, Instant.class);
    }

    public void putIssuedAt(Instant issuedAt) {
        put(JwtClaimNames.ISSUED_AT, issuedAt);
    }

    public String getId() {
        return get(JwtClaimNames.ID, String.class);
    }

    public void putId(String id) {
        put(JwtClaimNames.ID, id);
    }

    @Override
    public Object put(String key, Object value) {
        switch (key) {
            case JwtClaimNames.EXPIRATION, JwtClaimNames.NOT_BEFORE, JwtClaimNames.ISSUED_AT -> {
                if (value instanceof Instant instant) {
                    return super.put(key, (instant).getEpochSecond());
                } else if (value instanceof Date date) {
                    return super.put(key, date.getTime() / 1000);
                } else {
                    return super.put(key, value);
                }
            }
            default -> {
                return super.put(key, value);
            }
        }
    }

    public <T> T get(String key, Class<T> targetType) {
        Object value = get(key);
        if (value == null) {
            return null;
        }

        switch (key) {
            case JwtClaimNames.EXPIRATION:
            case JwtClaimNames.NOT_BEFORE:
            case JwtClaimNames.ISSUED_AT:
                if (value instanceof Number) {
                    if (targetType.equals(Instant.class)) {
                        value = Instant.ofEpochSecond((long) value);
                    } else if (targetType.equals(Date.class)) {
                        value = new Date((long) value * 1000);
                    }
                }
                break;
            default:
                if (value instanceof Number) {
                    if (Long.class.equals(targetType)) {
                        value = ((Number) value).longValue();
                    } else if (Integer.class.equals(targetType)) {
                        value = ((Number) value).intValue();
                    } else if (Short.class.equals(targetType)) {
                        value = ((Number) value).shortValue();
                    } else if (Byte.class.equals(targetType)) {
                        value = ((Number) value).byteValue();
                    }
                }
        }

        return targetType.cast(value);
    }
}
