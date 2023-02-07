package org.navistack.framework.captcha.simplecaptcha;

import jakarta.servlet.http.HttpServletRequest;
import org.navistack.framework.captcha.CaptchaTester;

public class SimpleCaptchaTester implements CaptchaTester {
    private final SimpleCaptchaService simpleCaptchaService;

    public SimpleCaptchaTester(SimpleCaptchaService simpleCaptchaService) {
        this.simpleCaptchaService = simpleCaptchaService;
    }

    @Override
    public boolean test(HttpServletRequest request) {
        return simpleCaptchaService.validate(request.getParameter("ticket"));
    }
}
