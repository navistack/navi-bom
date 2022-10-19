package org.navistack.framework.web.rest.exceptionhandling.translators.core.error;

import org.navistack.framework.core.error.UserException;
import org.navistack.framework.web.rest.RestResult;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslation;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;

public class UserExceptionTranslator implements ExceptionTranslator {
    @Override
    public ExceptionTranslation translate(Throwable throwable) {
        UserException exception = (UserException) throwable;
        RestResult.ParameterizedError error = RestResult.ParameterizedError.of(
                exception.getErrorCode(),
                exception.getMessage()
        );
        return ExceptionTranslation.of(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return UserException.class.isAssignableFrom(throwableType);
    }
}
