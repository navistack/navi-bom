package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.security.access;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.core.error.UserErrorCodes;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface AccessDeniedExceptionHandlerTrait extends ExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult.Err<? super RestResult.ParameterizedError>> handleAccessDenied(
            AccessDeniedException exception
    ) {
        return toResponse(
                exception,
                RestResult.ParameterizedError.of(
                        UserErrorCodes.AUTHORIZATION_FAILURE,
                        exception.getMessage()
                ),
                HttpStatus.FORBIDDEN
        );
    }
}
