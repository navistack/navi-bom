package org.navistack.framework.security;

import jakarta.servlet.http.HttpServletRequest;

public interface BearerTokenResolver {
    String resolveToken(HttpServletRequest request);
}
