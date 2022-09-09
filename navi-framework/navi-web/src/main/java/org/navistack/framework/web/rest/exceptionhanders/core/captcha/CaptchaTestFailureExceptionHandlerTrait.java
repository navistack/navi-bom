package org.navistack.framework.web.rest.exceptionhanders.core.captcha;

import org.navistack.framework.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.captcha.CaptchaTestFailureException;
import org.navistack.framework.core.error.UserErrorCodes;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public interface CaptchaTestFailureExceptionHandlerTrait extends ExceptionHandlerTrait {
    @ExceptionHandler
    default ResponseEntity<RestResult.Err<? super RestResult.ThrowableError>> handleCaptchaTestFailure(
            CaptchaTestFailureException exception,
            HttpServletRequest request
    ) {
        RestResult.ThrowableError error = RestResult.ThrowableError.of(
                UserErrorCodes.CAPTCHA_TEST_FAILED,
                exception.getMessage(),
                exception
        );
        return toResponse(error, HttpStatus.BAD_REQUEST, request);
    }
}
