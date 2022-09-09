package org.navistack.framework.web.rest.exceptionhanders.web;

import org.navistack.framework.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.core.error.UserErrorCodes;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public interface HttpRequestMethodNotSupportedExceptionHandlerTrait extends ExceptionHandlerTrait {

    @ExceptionHandler
    default ResponseEntity<RestResult.Err<? super RestResult.ThrowableError>> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception,
            HttpServletRequest request
    ) {
        RestResult.ThrowableError error = RestResult.ThrowableError.of(
                UserErrorCodes.ILLEGAL_REQUEST,
                exception.getMessage(),
                exception
        );
        return toResponse(
                error,
                HttpStatus.METHOD_NOT_ALLOWED,
                request
        );
    }
}
