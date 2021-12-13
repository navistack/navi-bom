package org.navistack.framework.captcha.simplecaptcha;

import org.navistack.framework.captcha.CaptchaTester;

import javax.servlet.http.HttpServletRequest;

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
