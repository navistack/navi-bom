package org.navistack.framework.web.rest.exceptionhandling.translators.web.servlet;

import org.navistack.framework.core.error.UserErrors;
import org.navistack.framework.web.rest.RestErrResult;
import org.navistack.framework.web.rest.RestResults;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

public class NoHandlerFoundExceptionTranslator implements ExceptionTranslator {
    @Override
    public RestErrResult translate(Throwable throwable) {
        return RestResults.err(throwable)
                .setError(UserErrors.UNKNOWN_ENDPOINT)
                .setStatus(HttpStatus.NOT_FOUND);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return NoHandlerFoundException.class.isAssignableFrom(throwableType);
    }
}
