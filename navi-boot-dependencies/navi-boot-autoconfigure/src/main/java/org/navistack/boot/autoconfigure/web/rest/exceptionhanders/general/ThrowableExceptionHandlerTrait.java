package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.general;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.core.error.UncategorizedErrorCodes;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface ThrowableExceptionHandlerTrait extends ExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult.Err<? super RestResult.ParameterizedError>> handleThrowable(
            Throwable throwable
    ) {
        return toResponse(
                throwable,
                RestResult.ParameterizedError.of(
                        UncategorizedErrorCodes.UNCATEGORIZED_ERROR,
                        "Internal Server Error"
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
