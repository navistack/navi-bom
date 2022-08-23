package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.web.servlet;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.core.error.UserErrorCodes;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

public interface NoHandlerFoundExceptionHandlerTrait extends ExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult.Err<? super RestResult.ParameterizedError>> handleNoHandlerFoundException(
            NoHandlerFoundException exception
    ) {
        return toResponse(
                exception,
                RestResult.ParameterizedError.of(UserErrorCodes.UNKNOWN_ENDPOINT, exception.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
