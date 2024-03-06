package org.navistack.framework.web.rest.exceptionhandling.translators.security.access;

import org.navistack.framework.core.error.UserErrors;
import org.navistack.framework.web.rest.RestErrResult;
import org.navistack.framework.web.rest.RestResults;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;

public class AccessDeniedExceptionTranslator implements ExceptionTranslator {
    @Override
    public RestErrResult translate(Throwable throwable) {
        return RestResults.err(throwable)
                .setError(UserErrors.AUTHORIZATION_FAILURE)
                .setStatus(HttpStatus.FORBIDDEN);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return AccessDeniedException.class.isAssignableFrom(throwableType);
    }
}
