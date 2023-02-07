package org.navistack.smoketest.ratelimit.rest;

import jakarta.servlet.http.HttpServletRequest;
import org.navistack.framework.ratelimit.FixedWindowRateLimit;
import org.navistack.framework.ratelimit.SlidingWindowRateLimit;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    @GetMapping("/rate-limited/sliding-window/ping")
    @SlidingWindowRateLimit(key = "echo.#{#request.remoteAddr}", maxRequests = 1, windowSize = 2000)
    public RestResult<Void, ?> slidingWindowRateLimitedPing(HttpServletRequest request) {
        return RestResult.ok();
    }

    @GetMapping("/rate-limited/fixed-window/ping")
    @FixedWindowRateLimit(key = "echo.#{#request.remoteAddr}", maxRequests = 1)
    public RestResult<Void, ?> fixedWindowRateLimitedPing(HttpServletRequest request) {
        return RestResult.ok();
    }
}
