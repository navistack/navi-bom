package org.navistack.boot.autoconfigure.web.rest.exceptionhandling.security.access;

import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.common.AdviceTrait;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface AccessDeniedAdviceTrait extends AdviceTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult<Void, RestResult.SimpleError>> handleAccessDenied(
            AccessDeniedException exception
    ) {
        return toResponse(
                exception,
                RestResult.SimpleError.of(
                        "AccessDenied",
                        exception.getMessage()
                ),
                HttpStatus.FORBIDDEN
        );
    }
}
