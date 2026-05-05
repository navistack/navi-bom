package org.navistack.smoketest.ratelimit.rest;

import jakarta.servlet.http.HttpServletRequest;
import org.navistack.framework.ratelimit.PeriodicRateLimit;
import org.navistack.framework.ratelimit.RollingRateLimit;
import org.navistack.framework.web.rest.RestResult;
import org.navistack.framework.web.rest.RestResults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    @GetMapping("/rate-limited/rolling/ping")
    @RollingRateLimit(key = "echo.#{#request.remoteAddr}", maxRequests = 1, windowSize = 2)
    public RestResult<Void> rollingRateLimitedPing(HttpServletRequest request) {
        return RestResults.ok();
    }

    @GetMapping("/rate-limited/periodic/ping")
    @PeriodicRateLimit(key = "echo.#{#request.remoteAddr}", maxRequests = 1)
    public RestResult<Void> periodicRateLimitedPing(HttpServletRequest request) {
        return RestResults.ok();
    }
}
