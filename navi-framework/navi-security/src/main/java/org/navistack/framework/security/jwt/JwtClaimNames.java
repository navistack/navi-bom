package org.navistack.framework.security.jwt;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JwtClaimNames {
    public final String ISSUER = "iss";

    public final String SUBJECT = "sub";

    public final String AUDIENCE = "aud";

    public final String EXPIRATION = "exp";

    public final String NOT_BEFORE = "nbf";

    public final String ISSUED_AT = "iat";

    public final String ID = "jti";
}
