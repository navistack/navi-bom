package org.navistack.boot.autoconfigure.web.rest.exceptionhandling.general;

import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.common.AdviceTrait;
import org.navistack.framework.core.problem.Problem;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface ProblemAdviceTrait extends AdviceTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult<Void, RestResult.ParameterizedError>> handleProblem(
            Problem problem
    ) {
        return toResponse(
                problem,
                RestResult.ParameterizedError.of(
                        problem.getError(),
                        problem.getMessage(),
                        problem.getParameters()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
