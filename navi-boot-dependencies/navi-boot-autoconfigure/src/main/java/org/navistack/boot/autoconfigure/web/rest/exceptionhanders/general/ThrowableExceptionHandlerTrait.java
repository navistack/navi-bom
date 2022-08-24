package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.general;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.core.error.UncategorizedErrorCodes;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public interface ThrowableExceptionHandlerTrait extends ExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult.Err<? super RestResult.ThrowableError>> handleThrowable(
            Throwable throwable,
            HttpServletRequest request
    ) {
        RestResult.ThrowableError error = RestResult.ThrowableError.of(
                UncategorizedErrorCodes.UNCATEGORIZED_ERROR,
                "Internal Server Error",
                throwable
        );
        return toResponse(error, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
