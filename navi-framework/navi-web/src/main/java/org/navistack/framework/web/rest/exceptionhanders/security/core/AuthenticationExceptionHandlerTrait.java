package org.navistack.framework.web.rest.exceptionhanders.security.core;

import org.navistack.framework.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.core.problem.PlatformProblems;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface AuthenticationExceptionHandlerTrait extends ExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult<Void, RestResult.SimpleError>> handleAuthentication(
            AuthenticationException exception
    ) {
        return toResponse(
                exception,
                RestResult.SimpleError.of(
                        PlatformProblems.AUTHENTICATION_FAILURE,
                        exception.getMessage()
                ),
                HttpStatus.UNAUTHORIZED
        );
    }
}
