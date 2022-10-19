package org.navistack.framework.web.rest.exceptionhandling.translators.captcha;

import org.navistack.framework.captcha.CaptchaTestFailureException;
import org.navistack.framework.core.error.UserErrorCodes;
import org.navistack.framework.web.rest.RestResult;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslation;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;

public class CaptchaTestFailureExceptionTranslator implements ExceptionTranslator {
    @Override
    public ExceptionTranslation translate(Throwable throwable) {
        RestResult.ParameterizedError error = RestResult.ParameterizedError.of(
                UserErrorCodes.CAPTCHA_TEST_FAILED,
                throwable.getMessage()
        );
        return ExceptionTranslation.of(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return CaptchaTestFailureException.class.isAssignableFrom(throwableType);
    }
}
