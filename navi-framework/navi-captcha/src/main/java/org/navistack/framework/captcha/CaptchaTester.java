package org.navistack.framework.captcha;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface CaptchaTester {
    boolean test(HttpServletRequest request);
}
