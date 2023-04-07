package org.navistack.framework.security.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.navistack.framework.security.TokenService;
import org.navistack.framework.security.TokenServiceAuthenticationException;
import org.navistack.framework.security.TokenServiceException;
import org.navistack.framework.security.TokenServiceIssueException;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.sql.Date;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;

/**
 * JSON Web Token Generator
 */
@Slf4j
public class JwtTokenService implements TokenService {
    private final static int DEFAULT_VALIDITY = 2 * 60 * 60 * 1000;

    private JwtPayloadResolver payloadResolver;

    /**
     * validity in milliseconds
     */
    private final int validity;

    private final JWSSigner jwsSigner;

    private final JWSVerifier jwsVerifier;

    public JwtTokenService() {
        this(DEFAULT_VALIDITY);
    }

    public JwtTokenService(int validity) {
        this(generateRandomSecretKey(), validity);
    }

    public JwtTokenService(String base64encodedKey) {
        this(base64encodedKey, DEFAULT_VALIDITY);
    }

    public JwtTokenService(byte[] keyInBytes) {
        this(keyInBytes, DEFAULT_VALIDITY);
    }

    public JwtTokenService(SecretKey secretKey) {
        this(secretKey, DEFAULT_VALIDITY);
    }

    public JwtTokenService(String base64encodedKey, int validity) {
        this(
                Base64.getDecoder().decode(base64encodedKey.getBytes(StandardCharsets.UTF_8)),
                validity
        );
    }

    public JwtTokenService(SecretKey secretKey, int validity) {
        this(secretKey.getEncoded(), validity);
    }

    public JwtTokenService(byte[] secretKey, int validity) {
        try {
            this.jwsSigner = new MACSigner(secretKey);
            this.jwsVerifier = new MACVerifier(secretKey);
            this.validity = validity;
        } catch (JOSEException e) {
            throw new TokenServiceException(e);
        }
    }

    public JwtPayloadResolver getPayloadResolver() {
        return payloadResolver;
    }

    public void setPayloadResolver(JwtPayloadResolver payloadResolver) {
        this.payloadResolver = payloadResolver;
    }

    @Override
    public String issue(Authentication authentication) throws TokenServiceIssueException {
        JwtClaims claims = payloadResolver.getClaims(authentication);
        claims.putExpiration(Instant.now().plus(validity, ChronoUnit.MILLIS));
        JWTClaimsSet jwtClaimsSet = convert(claims);
        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader(JWSAlgorithm.HS512),
                jwtClaimsSet
        );
        try {
            signedJWT.sign(jwsSigner);
        } catch (JOSEException e) {
            throw new TokenServiceIssueException("Failed to sign token", e);
        }
        return signedJWT.serialize();
    }

    @Override
    public Authentication authenticate(String token) throws TokenServiceAuthenticationException {
        JwtClaims claims = parseAndGetPayload(token);

        Instant now = Instant.now();

        Instant expiration = claims.getExpiration();
        if (expiration == null) {
            log.warn("Never-expiring token received: {}", token);
        } else if (expiration.isBefore(now)) {
            throw new TokenServiceAuthenticationException("Expired token");
        }

        Instant notBefore = claims.getNotBefore();
        if (notBefore != null && notBefore.isAfter(now)) {
            throw new TokenServiceAuthenticationException("Ineffective token");
        }

        return payloadResolver.getAuthentication(claims);
    }

    @Override
    public boolean validate(String token) {
        try {
            return authenticate(token) != null;
        } catch (TokenServiceAuthenticationException e) {
            return false;
        }
    }

    private JwtClaims parseAndGetPayload(String token) {
        try {
            if (token == null) {
                throw new TokenServiceAuthenticationException("Empty Token");
            }

            token = token.trim();
            if (token.isEmpty()) {
                throw new TokenServiceAuthenticationException("Empty Token");
            }

            SignedJWT jwt = SignedJWT.parse(token);

            if (!jwt.verify(jwsVerifier)) {
                throw new TokenServiceAuthenticationException("Invalid Token");
            }

            return convertBack(jwt.getJWTClaimsSet());
        } catch (ParseException | JOSEException e) {
            throw new TokenServiceAuthenticationException("Malformed Token", e);
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

    private static byte[] generateRandomSecretKey() {
        RandomGenerator random = new SecureRandom();
        byte[] key = new byte[64];
        random.nextBytes(key);
        return key;
    }
}
