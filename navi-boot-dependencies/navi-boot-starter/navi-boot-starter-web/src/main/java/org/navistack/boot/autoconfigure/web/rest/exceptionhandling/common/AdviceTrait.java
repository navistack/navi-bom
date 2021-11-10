package org.navistack.boot.autoconfigure.web.rest.exceptionhandling.common;

import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface AdviceTrait {
    default <E> ResponseEntity<RestResult<Void, E>> toResponse(Throwable throwable, E error, HttpStatus httpStatus) {
        AdviceTraits.log(throwable, httpStatus);

        return new ResponseEntity<> (RestResult.err(error), httpStatus);
    }
}
