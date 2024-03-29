package org.navistack.smoketest.captcha.rest;

import org.navistack.framework.web.rest.RestResult;
import org.navistack.framework.web.rest.RestResults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    @GetMapping("/ping")
    public RestResult<Void> echo() {
        return RestResults.ok();
    }
}
