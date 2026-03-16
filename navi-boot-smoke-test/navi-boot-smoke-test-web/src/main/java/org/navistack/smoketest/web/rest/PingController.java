package org.navistack.smoketest.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    @RequestMapping("/ping")
    public void ping() {
    }
}
