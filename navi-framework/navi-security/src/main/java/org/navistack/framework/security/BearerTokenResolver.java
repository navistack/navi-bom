package org.navistack.framework.security;

import javax.servlet.http.HttpServletRequest;

public interface BearerTokenResolver {
    String resolveToken(HttpServletRequest request);
}
