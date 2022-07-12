package org.navistack.framework.web.rest.exceptionhanders.common;

import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ExceptionHandlerTrait {
    default <E> ResponseEntity<RestResult<Void, E>> toResponse(Throwable throwable, E error, HttpStatus httpStatus) {
        LogUtils.log(throwable, httpStatus);

        return new ResponseEntity<> (RestResult.err(error), httpStatus);
    }
}
