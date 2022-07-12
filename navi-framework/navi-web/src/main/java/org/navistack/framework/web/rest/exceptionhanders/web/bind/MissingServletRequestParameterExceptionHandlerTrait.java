package org.navistack.framework.web.rest.exceptionhanders.web.bind;

import org.navistack.framework.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.core.problem.PlatformProblems;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface MissingServletRequestParameterExceptionHandlerTrait extends ExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult<Void, RestResult.SimpleError>> handleMissingServletRequestParameter(
            MissingServletRequestParameterException exception
    ) {
        return toResponse(
                exception,
                RestResult.SimpleError.of(PlatformProblems.MISSING_PARAMETER, exception.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
