package org.navistack.framework.captcha;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CaptchaTestInterceptor implements HandlerInterceptor {
    private final CaptchaTester captchaTester;

    @Getter
    @Setter
    private boolean checkForAnnotation = true;

    public CaptchaTestInterceptor(CaptchaTester captchaTester) {
        this.captchaTester = captchaTester;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        do {
            if (!checkForAnnotation) {
                break;
            }

            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                if (handlerMethod.hasMethodAnnotation(CaptchaTest.class)) {
                    break;
                }
            }

            return true;
        } while (false);

        if (!captchaTester.test(request)) {
            throw new CaptchaTestFailureException("CAPTCHA test failed");
        }

        return true;
    }
}
