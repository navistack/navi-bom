package org.navistack.framework.security;

import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class DefaultBearerTokenResolver implements BearerTokenResolver {
    public static final String AUTHORIZATION_HEADER = HttpHeaders.AUTHORIZATION;
    public static final String AUTHORIZATION_TOKEN = "access_token";

    @Override
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        String token = request.getParameter(AUTHORIZATION_TOKEN);
        if (StringUtils.hasText(token)) {
            return token;
        }
        return null;
    }
}
