package org.navistack.boot.autoconfigure.web.rest.exceptionhandling.web.bind;

import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.common.AdviceTrait;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface MissingServletRequestParameterAdviceTrait extends AdviceTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult<Void, RestResult.SimpleError>> handleMissingServletRequestParameter(
            MissingServletRequestParameterException exception
    ) {
        return toResponse(
                exception,
                RestResult.SimpleError.of("ParameterMissing", exception.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
