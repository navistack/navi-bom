package org.navistack.boot.autoconfigure.web.rest.exceptionhandling.web.servlet;

import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.common.AdviceTrait;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

public interface NoHandlerFoundExceptionAdviceTrait extends AdviceTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult<Void, RestResult.SimpleError>> handleNoHandlerFoundException(
            NoHandlerFoundException exception
    ) {
        return toResponse(
                exception,
                RestResult.SimpleError.of("EndpointUnknown", exception.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
