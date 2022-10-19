package org.navistack.framework.web.rest.exceptionhandling.translators.web;

import org.navistack.framework.core.error.UserErrorCodes;
import org.navistack.framework.web.rest.RestResult;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslation;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;

public class HttpRequestMethodNotSupportedExceptionTranslator implements ExceptionTranslator {
    @Override
    public ExceptionTranslation translate(Throwable throwable) {
        RestResult.ParameterizedError error = RestResult.ParameterizedError.of(
                UserErrorCodes.ILLEGAL_REQUEST,
                throwable.getMessage()
        );
        return ExceptionTranslation.of(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return HttpRequestMethodNotSupportedException.class.isAssignableFrom(throwableType);
    }
}
