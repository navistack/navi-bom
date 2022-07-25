package org.navistack.framework.security.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * JSON Web Token Generator
 */
@Slf4j
public class DefaultJwtTokenService implements JwtTokenService {
    private final static int DEFAULT_VALIDITY = 2 * 60 * 60 * 1000;

    private JwtTokenResolver tokenResolver;

    /**
     * validity in milliseconds
     */
    private final int validity;

    private final JWSSigner jwsSigner;

    private final JWSVerifier jwsVerifier;

    public DefaultJwtTokenService(String base64encodedKey) {
        this(base64encodedKey, DEFAULT_VALIDITY);
    }

    public DefaultJwtTokenService(byte[] keyInBytes) {
        this(keyInBytes, DEFAULT_VALIDITY);
    }

    public DefaultJwtTokenService(SecretKey secretKey) {
        this(secretKey, DEFAULT_VALIDITY);
    }

    public DefaultJwtTokenService(String base64encodedKey, int validity) {
        this(
                Base64.getDecoder().decode(base64encodedKey.getBytes(StandardCharsets.UTF_8)),
                validity
        );
    }

    public DefaultJwtTokenService(SecretKey secretKey, int validity) {
        this(secretKey.getEncoded(), validity);
    }

    public DefaultJwtTokenService(byte[] secretKey, int validity) {
        try {
            this.jwsSigner = new MACSigner(secretKey);
            this.jwsVerifier = new MACVerifier(secretKey);
            this.validity = validity;
        } catch (JOSEException e) {
            throw new JwtTokenServiceException(e);
        }
    }

    public JwtTokenResolver getTokenResolver() {
        return tokenResolver;
    }

    public void setTokenResolver(JwtTokenResolver tokenResolver) {
        this.tokenResolver = tokenResolver;
    }

    @Override
    public String issue(Authentication authentication) {
        JwtClaims claims = tokenResolver.getClaims(authentication);
        claims.putExpiration(Instant.now().plus(validity, ChronoUnit.MILLIS));
        JWTClaimsSet jwtClaimsSet = convert(claims);
        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader(JWSAlgorithm.HS512),
                jwtClaimsSet
        );
        try {
            signedJWT.sign(jwsSigner);
        } catch (JOSEException e) {
            throw new JwtIssueException("Failed to sign token", e);
        }
        return signedJWT.serialize();
    }

    @Override
    public Authentication authenticate(String token) throws JwtAuthenticationException {
        JwtClaims claims = parseAndGetPayload(token);

        Instant now = Instant.now();

        Instant expiration = claims.getExpiration();
        if (expiration == null) {
            log.warn("Never-expiring token received: {}", token);
        } else if (expiration.isBefore(now)) {
            throw new JwtAuthenticationException("Expired token");
        }

        Instant notBefore = claims.getNotBefore();
        if (notBefore != null && notBefore.isAfter(now)) {
            throw new JwtAuthenticationException("Ineffective token");
        }

        return tokenResolver.getAuthentication(claims);
    }

    @Override
    public boolean validate(String token) {
        try {
            return authenticate(token) != null;
        } catch (JwtAuthenticationException e) {
            return false;
        }
    }

    private JwtClaims parseAndGetPayload(String token) {
        try {
            if (token == null) {
                throw new JwtAuthenticationException("Empty Token");
            }

            token = token.trim();
            if (token.isEmpty()) {
                throw new JwtAuthenticationException("Empty Token");
            }

            SignedJWT jwt = SignedJWT.parse(token);

            if (!jwt.verify(jwsVerifier)) {
                throw new JwtAuthenticationException("Invalid Token");
            }

            return convertBack(jwt.getJWTClaimsSet());
        } catch (ParseException | JOSEException e) {
            throw new JwtAuthenticationException("Malformed Token", e);
        }
    }

    private static JWTClaimsSet convert(JwtClaims claims) {
        if (claims == null) {
            return null;
        }

        JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();

        String issuer = claims.getIssuer();
        if (issuer != null) {
            builder.issuer(issuer);
        }

        String subject = claims.getSubject();
        if (subject != null) {
            builder.subject(subject);
        }

        String audience = claims.getAudience();
        if (audience != null) {
            builder.audience(audience);
        }

        Instant expiration = claims.getExpiration();
        if (expiration != null) {
            builder.expirationTime(Date.from(expiration));
        }

        Instant notBefore = claims.getNotBefore();
        if (notBefore != null) {
            builder.notBeforeTime(Date.from(notBefore));
        }

        Instant issuedAt = claims.getIssuedAt();
        if (issuedAt != null) {
            builder.issueTime(Date.from(issuedAt));
        }

        String id = claims.getId();
        if (id != null) {
            builder.jwtID(id);
        }

        for (Map.Entry<String, Object> claim : claims.entrySet()) {
            if (JWTClaimsSet.getRegisteredNames().contains(claim.getKey())) {
                continue;
            }
            builder.claim(claim.getKey(), claim.getValue());
        }

        return builder.build();
    }

    private static JwtClaims convertBack(JWTClaimsSet jwtClaimsSet) {
        if (jwtClaimsSet == null) {
            return null;
        }

        JwtClaims claims = new DefaultJwtClaims();

        String issuer = jwtClaimsSet.getIssuer();
        if (issuer != null) {
            claims.putIssuer(issuer);
        }

        String subject = jwtClaimsSet.getSubject();
        if (subject != null) {
            claims.putSubject(subject);
        }

        List<String> audiences = jwtClaimsSet.getAudience();
        if (audiences != null && !audiences.isEmpty()) {
            claims.putAudience(audiences.get(0));
        }

        java.util.Date expirationTime = jwtClaimsSet.getExpirationTime();
        if (expirationTime != null) {
            claims.putExpiration(expirationTime.toInstant());
        }

        java.util.Date notBeforeTime = jwtClaimsSet.getNotBeforeTime();
        if (notBeforeTime != null) {
            claims.putNotBefore(notBeforeTime.toInstant());
        }

        java.util.Date issueTime = jwtClaimsSet.getIssueTime();
        if (issueTime != null) {
            claims.putIssuedAt(issueTime.toInstant());
        }

        String jwtid = jwtClaimsSet.getJWTID();
        if (jwtid != null) {
            claims.putId(jwtid);
        }

        for (Map.Entry<String, Object> claim : jwtClaimsSet.getClaims().entrySet()) {
            if (JWTClaimsSet.getRegisteredNames().contains(claim.getKey())) {
                continue;
            }
            claims.put(claim.getKey(), claim.getValue());
        }

        return claims;
    }
}
