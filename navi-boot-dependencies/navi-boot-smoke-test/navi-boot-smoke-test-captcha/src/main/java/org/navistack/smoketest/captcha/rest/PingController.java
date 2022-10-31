package org.navistack.smoketest.captcha.rest;

import org.navistack.framework.captcha.CaptchaTest;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    @GetMapping("/ping")
    @CaptchaTest
    public RestResult<Void, ?> echo() {
        return RestResult.ok();
    }
}
