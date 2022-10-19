package org.navistack.framework.web.rest.exceptionhandling.translators.core.error;

import org.navistack.framework.core.error.CodedException;
import org.navistack.framework.web.rest.RestResult;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslation;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;

public class CodedExceptionTranslator implements ExceptionTranslator {
    @Override
    public ExceptionTranslation translate(Throwable throwable) {
        CodedException exception = (CodedException) throwable;
        RestResult.ParameterizedError error = RestResult.ParameterizedError.of(
                exception.getErrorCode(),
                exception.getMessage()
        );
        return ExceptionTranslation.of(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return CodedException.class.isAssignableFrom(throwableType);
    }
}
