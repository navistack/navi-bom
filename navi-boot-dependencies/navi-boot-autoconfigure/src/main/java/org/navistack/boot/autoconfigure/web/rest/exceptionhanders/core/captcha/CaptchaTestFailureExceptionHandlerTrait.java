package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.core.captcha;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.captcha.CaptchaTestFailureException;
import org.navistack.framework.core.error.UserErrorCodes;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface CaptchaTestFailureExceptionHandlerTrait extends ExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult.Err<? super RestResult.ParameterizedError>> handleCaptchaTestFailure(
            CaptchaTestFailureException exception
    ) {
        return toResponse(
                exception,
                RestResult.ParameterizedError.of(
                        UserErrorCodes.CAPTCHA_TEST_FAILED,
                        exception.getMessage()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
