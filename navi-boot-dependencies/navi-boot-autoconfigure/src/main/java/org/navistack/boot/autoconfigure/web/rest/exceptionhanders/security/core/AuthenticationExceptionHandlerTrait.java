package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.security.core;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.core.error.UserErrorCodes;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface AuthenticationExceptionHandlerTrait extends ExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult.Err<? super RestResult.ParameterizedError>> handleAuthentication(
            AuthenticationException exception
    ) {
        return toResponse(
                exception,
                RestResult.ParameterizedError.of(
                        UserErrorCodes.AUTHENTICATION_FAILURE,
                        exception.getMessage()
                ),
                HttpStatus.UNAUTHORIZED
        );
    }
}
