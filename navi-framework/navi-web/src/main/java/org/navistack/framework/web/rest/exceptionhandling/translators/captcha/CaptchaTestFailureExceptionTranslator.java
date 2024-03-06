package org.navistack.framework.web.rest.exceptionhandling.translators.captcha;

import org.navistack.framework.captcha.CaptchaTestFailureException;
import org.navistack.framework.core.error.UserErrors;
import org.navistack.framework.web.rest.RestErrResult;
import org.navistack.framework.web.rest.RestResults;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;

public class CaptchaTestFailureExceptionTranslator implements ExceptionTranslator {
    @Override
    public RestErrResult translate(Throwable throwable) {
        return RestResults.err(throwable)
                .setError(UserErrors.CAPTCHA_TEST_FAILED)
                .setStatus(HttpStatus.BAD_REQUEST);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return CaptchaTestFailureException.class.isAssignableFrom(throwableType);
    }
}
