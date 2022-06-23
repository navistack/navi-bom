package org.navistack.boot.autoconfigure.web.rest.exceptionhandling.web.bind;

import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.validation.BaseValidationAdviceTrait;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface MethodArgumentNotValidAdviceTrait extends BaseValidationAdviceTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult<Void, RestResult.ParameterizedError>> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception
    ) {
        return toResponse(exception, fromBindingResult(exception.getBindingResult()));
    }
}
