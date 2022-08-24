package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.core.error;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.core.error.CodedException;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public interface CodedExceptionHandlerTrait extends ExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult.Err<? super RestResult.ThrowableError>> handleCodedException(
            CodedException exception,
            HttpServletRequest request
    ) {
        RestResult.ThrowableError error = RestResult.ThrowableError.of(
                exception.getErrorCode(),
                exception.getMessage(),
                exception
        );
        return toResponse(error, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
