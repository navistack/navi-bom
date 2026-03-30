package org.navistack.framework.security;

import jakarta.servlet.http.HttpServletRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultBearerTokenResolver implements BearerTokenResolver {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_TOKEN = "access_token";
    private static final Pattern BEARER_TOKEN_PATTERN = Pattern.compile("^Bearer ([a-zA-Z0-9-._~+/]+=*)$");

    @Override
    public String resolveToken(HttpServletRequest request) {
        String authorizationValue = request.getHeader(AUTHORIZATION_HEADER);

        if (authorizationValue != null) {
            Matcher matcher = BEARER_TOKEN_PATTERN.matcher(authorizationValue);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }

        String token = request.getParameter(AUTHORIZATION_TOKEN);
        if (token != null && !token.isBlank()) {
            return token;
        }

        return null;
    }
}
