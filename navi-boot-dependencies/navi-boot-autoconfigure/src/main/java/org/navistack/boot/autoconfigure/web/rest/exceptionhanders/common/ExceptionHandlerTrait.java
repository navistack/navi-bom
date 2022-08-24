package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.common;

import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface ExceptionHandlerTrait {

    <E extends RestResult.ThrowableError>
    ResponseEntity<RestResult.Err<? super E>> toResponse(E error, HttpStatus httpStatus, HttpServletRequest request);
}
