package org.navistack.framework.web.rest.exceptionhandling.translators.security.access;

import org.navistack.framework.core.error.UserErrorCodes;
import org.navistack.framework.web.rest.RestResult;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslation;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;

public class AccessDeniedExceptionTranslator implements ExceptionTranslator {
    @Override
    public ExceptionTranslation translate(Throwable throwable) {
        RestResult.ParameterizedError error = RestResult.ParameterizedError.of(
                UserErrorCodes.AUTHORIZATION_FAILURE,
                throwable.getMessage()
        );
        return ExceptionTranslation.of(error, HttpStatus.FORBIDDEN);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return AccessDeniedException.class.isAssignableFrom(throwableType);
    }
}
