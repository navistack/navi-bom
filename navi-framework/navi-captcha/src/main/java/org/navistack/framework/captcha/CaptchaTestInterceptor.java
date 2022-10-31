package org.navistack.framework.captcha;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CaptchaTestInterceptor implements HandlerInterceptor {
    private final CaptchaTester captchaTester;

    public CaptchaTestInterceptor(CaptchaTester captchaTester) {
        this.captchaTester = captchaTester;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (captchaTester.test(request)) {
            return true;
        }
        throw new CaptchaTestFailureException("CAPTCHA test failed");
    }
}
