package org.navistack.boot.autoconfigure.web.rest.exceptionhandling.security.core;

import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.common.AdviceTrait;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface AuthenticationAdviceTrait extends AdviceTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult<Void, RestResult.SimpleError>> handleAuthentication(
            AuthenticationException exception
    ) {
        return toResponse(
                exception,
                RestResult.SimpleError.of(
                        "AuthenticationFailed",
                        exception.getMessage()
                ),
                HttpStatus.UNAUTHORIZED
        );
    }
}
