package org.navistack.framework.captcha;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class CaptchaTestInterceptor implements HandlerInterceptor {
    private final CaptchaTester captchaTester;

    public CaptchaTestInterceptor(CaptchaTester captchaTester) {
        this.captchaTester = captchaTester;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (captchaTester.test(request)) {
            return true;
        }
        throw new CaptchaTestFailureException("CAPTCHA test failed");
    }
}
