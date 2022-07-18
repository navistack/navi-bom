package org.navistack.framework.web.rest.exceptionhanders.general;

import org.navistack.framework.core.problem.UncategorizedProblems;
import org.navistack.framework.web.rest.RestResult;
import org.navistack.framework.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface ThrowableExceptionHandlerTrait extends ExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult<Void, RestResult.SimpleError>> handleThrowable(
            Throwable throwable
    ) {
        if (includeStackTrace()) {
            return toResponse(
                    throwable,
                    RestResult.ExceptionalError.of(
                            UncategorizedProblems.UNKNOWN_PROBLEM,
                            "Internal Server Error",
                            throwable
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        } else {
            return toResponse(
                    throwable,
                    RestResult.SimpleError.of(
                            UncategorizedProblems.UNKNOWN_PROBLEM,
                            "Internal Server Error"
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
