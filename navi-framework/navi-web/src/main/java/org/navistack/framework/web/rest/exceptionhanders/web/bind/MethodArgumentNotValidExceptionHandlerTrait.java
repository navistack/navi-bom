package org.navistack.framework.web.rest.exceptionhanders.web.bind;

import org.navistack.framework.web.rest.exceptionhanders.validation.BaseValidationExceptionHandlerTrait;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface MethodArgumentNotValidExceptionHandlerTrait extends BaseValidationExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult<Void, RestResult.ParameterizedError>> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception
    ) {
        return toResponse(exception, fromBindingResult(exception.getBindingResult()));
    }
}
