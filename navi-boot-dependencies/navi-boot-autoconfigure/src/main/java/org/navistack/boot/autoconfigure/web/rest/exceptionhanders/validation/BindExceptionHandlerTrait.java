package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.validation;

import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public interface BindExceptionHandlerTrait extends BaseValidationExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult.Err<? super RestResult.ThrowableError>> handleBindException(
            BindException exception,
            HttpServletRequest request
    ) {
        return toResponse(exception, request, fromBindingResult(exception.getBindingResult()));
    }
}
