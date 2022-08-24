package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.web.bind;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.validation.BaseValidationExceptionHandlerTrait;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public interface MethodArgumentNotValidExceptionHandlerTrait extends BaseValidationExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult.Err<? super RestResult.ThrowableError>> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        return toResponse(exception, request, fromBindingResult(exception.getBindingResult()));
    }
}
