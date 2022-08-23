package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.core.domain;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.core.domain.DomainException;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface DomainExceptionHandlerTrait extends ExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult.Err<? super RestResult.ParameterizedError>> handleDomainException(
            DomainException exception
    ) {
        return toResponse(
                exception,
                RestResult.ParameterizedError.of(
                        exception.getErrorCode(),
                        exception.getMessage()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
