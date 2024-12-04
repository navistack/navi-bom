package org.navistack.framework.web.rest.exceptionhandling.translators.web;

import org.navistack.framework.core.error.ErrorCodes;
import org.navistack.framework.web.rest.RestErrResult;
import org.navistack.framework.web.rest.RestResults;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;

public class HttpRequestMethodNotSupportedExceptionTranslator implements ExceptionTranslator {
    @Override
    public RestErrResult translate(Throwable throwable) {
        return RestResults.err(throwable)
                .setError(ErrorCodes.ILLEGAL_REQUEST)
                .setStatus(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return HttpRequestMethodNotSupportedException.class.isAssignableFrom(throwableType);
    }
}
