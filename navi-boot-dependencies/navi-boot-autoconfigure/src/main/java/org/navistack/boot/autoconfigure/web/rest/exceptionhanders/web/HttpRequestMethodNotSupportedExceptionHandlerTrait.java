package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.web;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.core.error.UserErrorCodes;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface HttpRequestMethodNotSupportedExceptionHandlerTrait extends ExceptionHandlerTrait {

    @ExceptionHandler
    default ResponseEntity<RestResult.Err<? super RestResult.ParameterizedError>> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception
    ) {
        return toResponse(
                exception,
                RestResult.ParameterizedError.of(UserErrorCodes.ILLEGAL_REQUEST, exception.getMessage()),
                HttpStatus.METHOD_NOT_ALLOWED
        );
    }
}
