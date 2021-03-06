package org.navistack.framework.web.rest.exceptionhanders.security.access;

import org.navistack.framework.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.core.problem.PlatformProblems;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface AccessDeniedExceptionHandlerTrait extends ExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult<Void, RestResult.SimpleError>> handleAccessDenied(
            AccessDeniedException exception
    ) {
        return toResponse(
                exception,
                RestResult.SimpleError.of(
                        PlatformProblems.AUTHORIZATION_FAILURE,
                        exception.getMessage()
                ),
                HttpStatus.FORBIDDEN
        );
    }
}
