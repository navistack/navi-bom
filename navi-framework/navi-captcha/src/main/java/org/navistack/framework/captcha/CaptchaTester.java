package org.navistack.framework.captcha;

import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface CaptchaTester {
    boolean test(HttpServletRequest request);
}
