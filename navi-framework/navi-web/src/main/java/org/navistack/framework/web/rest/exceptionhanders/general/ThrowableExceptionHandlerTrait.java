package org.navistack.framework.web.rest.exceptionhanders.general;

import org.navistack.framework.core.problem.UncategorizedProblems;
import org.navistack.framework.web.rest.RestResult;
import org.navistack.framework.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public interface ThrowableExceptionHandlerTrait extends ExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult<Void, RestResult.SimpleError>> handleThrowable(
            Throwable throwable
    ) {
        if (includeStackTrace()) {
            return toResponse(
                    throwable,
                    RestResult.ParameterizedError.of(
                            UncategorizedProblems.UNKNOWN_PROBLEM,
                            "Internal Server Error",
                            fromThrowable(throwable)
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

    default Map<String, Object> fromThrowable(Throwable throwable) {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("exception", throwable.getClass().getName());

        StringWriter stackTrace = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stackTrace));
        stackTrace.flush();
        parameters.put("trace", stackTrace.toString());

        return parameters;
    }
}
