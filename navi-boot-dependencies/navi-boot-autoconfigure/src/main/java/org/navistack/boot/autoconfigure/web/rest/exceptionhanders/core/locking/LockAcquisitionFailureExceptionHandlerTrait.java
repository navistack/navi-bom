package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.core.locking;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.core.error.UserErrorCodes;
import org.navistack.framework.locking.LockAcquisitionFailureException;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public interface LockAcquisitionFailureExceptionHandlerTrait extends ExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult.Err<? super RestResult.ThrowableError>> handleLockAcquisitionFailure(
            LockAcquisitionFailureException exception,
            HttpServletRequest request
    ) {
        RestResult.ThrowableError error = RestResult.ThrowableError.of(
                UserErrorCodes.RESOURCE_LOCKED,
                exception.getMessage(),
                exception
        );
        return toResponse(error, HttpStatus.CONFLICT, request);
    }
}
