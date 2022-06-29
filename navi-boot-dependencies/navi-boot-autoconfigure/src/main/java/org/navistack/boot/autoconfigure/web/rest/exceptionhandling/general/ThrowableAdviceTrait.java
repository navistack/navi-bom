package org.navistack.boot.autoconfigure.web.rest.exceptionhandling.general;

import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.common.AdviceTrait;
import org.navistack.framework.core.problem.UncategorizedProblems;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface ThrowableAdviceTrait extends AdviceTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult<Void, RestResult.SimpleError>> handleThrowable(
            Throwable throwable
    ) {
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