package org.navistack.smoketest.captcha.rest;

import org.navistack.framework.captcha.simplecaptcha.AbstractSimpleCaptchaController;
import org.navistack.framework.captcha.simplecaptcha.SimpleCaptchaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/captcha")
public class CaptchaController extends AbstractSimpleCaptchaController {
    public CaptchaController(SimpleCaptchaService simpleCaptchaService) {
        super(simpleCaptchaService);
    }
}
