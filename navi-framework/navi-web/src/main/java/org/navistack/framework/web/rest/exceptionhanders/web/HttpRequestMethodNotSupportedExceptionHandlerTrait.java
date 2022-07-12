package org.navistack.framework.web.rest.exceptionhanders.web;

import org.navistack.framework.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.core.problem.PlatformProblems;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface HttpRequestMethodNotSupportedExceptionHandlerTrait extends ExceptionHandlerTrait {

    @ExceptionHandler
    default ResponseEntity<RestResult<Void, RestResult.SimpleError>> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception
    ) {
        return toResponse(
                exception,
                RestResult.SimpleError.of(PlatformProblems.ILLEGAL_REQUEST, exception.getMessage()),
                HttpStatus.METHOD_NOT_ALLOWED
        );
    }
}
