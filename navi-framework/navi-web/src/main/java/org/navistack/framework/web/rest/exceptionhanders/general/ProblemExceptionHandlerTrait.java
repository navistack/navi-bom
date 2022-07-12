package org.navistack.framework.web.rest.exceptionhanders.general;

import org.navistack.framework.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.core.problem.Problem;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface ProblemExceptionHandlerTrait extends ExceptionHandlerTrait {
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
