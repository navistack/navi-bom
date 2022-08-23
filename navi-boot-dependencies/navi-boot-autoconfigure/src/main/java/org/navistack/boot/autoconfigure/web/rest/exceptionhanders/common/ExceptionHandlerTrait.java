package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.common;

import org.navistack.framework.web.rest.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ExceptionHandlerTrait {
    Logger log = LoggerFactory.getLogger(ExceptionHandlerTrait.class);

    default boolean includeStackTrace() {
        return false;
    }

    default <E extends RestResult.ParameterizedError>
    ResponseEntity<RestResult.Err<? super E>> toResponse(Throwable throwable, E error, HttpStatus httpStatus) {
        log(throwable, httpStatus);

        if (includeStackTrace()) {
            return new ResponseEntity<>(RestResult.err(
                    RestResult.ExceptionalError.of(
                            error.getError(),
                            error.getMessage(),
                            error.getParameters(),
                            throwable
                    )
            ), httpStatus);
        } else {
            return new ResponseEntity<>(RestResult.err(error), httpStatus);
        }
    }

    static void log(Throwable throwable, HttpStatus httpStatus) {
        if (httpStatus.is4xxClientError()) {
            log.warn("{}: {}", httpStatus.getReasonPhrase(), throwable.getMessage());
        } else if (httpStatus.is5xxServerError()) {
            log.error(httpStatus.getReasonPhrase(), throwable);
        }
    }
}
