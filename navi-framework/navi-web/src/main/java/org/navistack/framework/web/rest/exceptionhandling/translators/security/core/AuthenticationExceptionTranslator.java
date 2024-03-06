package org.navistack.framework.web.rest.exceptionhandling.translators.security.core;

import org.navistack.framework.core.error.UserErrors;
import org.navistack.framework.web.rest.RestErrResult;
import org.navistack.framework.web.rest.RestResults;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class AuthenticationExceptionTranslator implements ExceptionTranslator {
    @Override
    public RestErrResult translate(Throwable throwable) {
        return RestResults.err(throwable)
                .setError(UserErrors.AUTHENTICATION_FAILURE)
                .setStatus(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return AuthenticationException.class.isAssignableFrom(throwableType);
    }
}
