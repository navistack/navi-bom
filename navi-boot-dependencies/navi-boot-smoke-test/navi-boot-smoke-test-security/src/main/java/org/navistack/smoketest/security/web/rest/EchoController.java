package org.navistack.smoketest.security.web.rest;

import org.navistack.framework.web.rest.RestResult;
import org.navistack.framework.web.rest.RestResults;
import org.navistack.smoketest.security.web.rest.vm.Echo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {
    @GetMapping("/authorized/role1/echo")
    @PreAuthorize("hasRole('role1')")
    public RestResult<Echo> echoRole1(String content) {
        return RestResults.ok(Echo.of(content));
    }

    @GetMapping("/authorized/role2/echo")
    @PreAuthorize("hasRole('role2')")
    public RestResult<Echo> echoRole2(String content) {
        return RestResults.ok(Echo.of(content));
    }

    @GetMapping("/echo")
    public RestResult<Echo> echo(String content) {
        return RestResults.ok(Echo.of(content));
    }

    @GetMapping("/sanitized/echo")
    public RestResult<Echo> echoSanitized(String content) {
        return RestResults.ok(Echo.of(content));
    }
}
