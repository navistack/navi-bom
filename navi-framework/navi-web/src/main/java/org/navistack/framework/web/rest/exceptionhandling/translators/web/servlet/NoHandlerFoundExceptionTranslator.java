package org.navistack.framework.web.rest.exceptionhandling.translators.web.servlet;

import org.navistack.framework.core.error.UserErrorCodes;
import org.navistack.framework.web.rest.RestResult;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslation;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

public class NoHandlerFoundExceptionTranslator implements ExceptionTranslator {
    @Override
    public ExceptionTranslation translate(Throwable throwable) {
        RestResult.ParameterizedError error = RestResult.ParameterizedError.of(
                UserErrorCodes.UNKNOWN_ENDPOINT,
                throwable.getMessage()
        );
        return ExceptionTranslation.of(error, HttpStatus.NOT_FOUND);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return NoHandlerFoundException.class.isAssignableFrom(throwableType);
    }
}
