package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.core.ratelimit;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.core.error.UserErrorCodes;
import org.navistack.framework.ratelimit.RateLimitExceededException;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public interface RateLimitExceededExceptionHandlerTrait extends ExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult.Err<? super RestResult.ThrowableError>> handleRateLimitExceeded(
            RateLimitExceededException exception,
            HttpServletRequest request
    ) {
        RestResult.ThrowableError error = RestResult.ThrowableError.of(
                UserErrorCodes.FREQUENT_REQUEST,
                exception.getMessage(),
                exception
        );
        return toResponse(error, HttpStatus.TOO_MANY_REQUESTS, request);
    }
}
