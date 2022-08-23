package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.core.error;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.core.error.UserException;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface UserExceptionHandlerTrait extends ExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult.Err<? super RestResult.ParameterizedError>> handleUserException(
            UserException exception
    ) {
        return toResponse(
                exception,
                RestResult.ParameterizedError.of(
                        exception.getErrorCode(),
                        exception.getMessage()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
