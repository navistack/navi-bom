package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.core.error;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.core.error.DomainException;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public interface DomainExceptionHandlerTrait extends ExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult.Err<? super RestResult.ThrowableError>> handleDomainException(
            DomainException exception,
            HttpServletRequest request
    ) {
        RestResult.ThrowableError error = RestResult.ThrowableError.of(
                exception.getErrorCode(),
                exception.getMessage(),
                exception
        );
        return toResponse(error, HttpStatus.BAD_REQUEST, request);
    }
}
