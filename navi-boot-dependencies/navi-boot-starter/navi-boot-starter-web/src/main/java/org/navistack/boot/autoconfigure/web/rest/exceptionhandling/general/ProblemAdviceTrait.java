package org.navistack.boot.autoconfigure.web.rest.exceptionhandling.general;

import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.common.AdviceTrait;
import org.navistack.framework.core.problem.ThrowableProblem;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface ProblemAdviceTrait extends AdviceTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult<Void, RestResult.ParameterizedError>> handleProblem(
            ThrowableProblem exception
    ) {
        return toResponse(
                exception,
                RestResult.ParameterizedError.of(
                        exception.getError(),
                        exception.getMessage(),
                        exception.getParameters()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
