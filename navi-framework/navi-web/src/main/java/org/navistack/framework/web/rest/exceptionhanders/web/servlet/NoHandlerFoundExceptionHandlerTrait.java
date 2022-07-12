package org.navistack.framework.web.rest.exceptionhanders.web.servlet;

import org.navistack.framework.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.core.problem.PlatformProblems;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

public interface NoHandlerFoundExceptionHandlerTrait extends ExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult<Void, RestResult.SimpleError>> handleNoHandlerFoundException(
            NoHandlerFoundException exception
    ) {
        return toResponse(
                exception,
                RestResult.SimpleError.of(PlatformProblems.UNKNOWN_ENDPOINT, exception.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
