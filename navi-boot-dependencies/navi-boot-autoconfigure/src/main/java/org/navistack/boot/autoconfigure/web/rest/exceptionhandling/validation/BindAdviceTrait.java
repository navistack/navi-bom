package org.navistack.boot.autoconfigure.web.rest.exceptionhandling.validation;

import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface BindAdviceTrait extends BaseValidationAdviceTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult<Void, RestResult.ParameterizedError>> handleBindException(
            BindException exception
    ) {
        return toResponse(exception, fromBindingResult(exception.getBindingResult()));
    }
}
