package org.navistack.smoketest.web.rest;

import org.navistack.framework.web.rest.RestResult;
import org.navistack.smoketest.web.rest.vm.Echo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {
    @GetMapping("/echo")
    public RestResult<Echo, ?> echo(@RequestParam String content) {
        return RestResult.ok(Echo.of(content));
    }
}
