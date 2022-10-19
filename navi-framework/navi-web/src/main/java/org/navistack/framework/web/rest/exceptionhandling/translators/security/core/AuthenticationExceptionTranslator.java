package org.navistack.framework.web.rest.exceptionhandling.translators.security.core;

import org.navistack.framework.core.error.UserErrorCodes;
import org.navistack.framework.web.rest.RestResult;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslation;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class AuthenticationExceptionTranslator implements ExceptionTranslator {
    @Override
    public ExceptionTranslation translate(Throwable throwable) {
        RestResult.ParameterizedError error = RestResult.ParameterizedError.of(
                UserErrorCodes.AUTHENTICATION_FAILURE,
                throwable.getMessage()
        );
        return ExceptionTranslation.of(error, HttpStatus.UNAUTHORIZED);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return AuthenticationException.class.isAssignableFrom(throwableType);
    }
}
