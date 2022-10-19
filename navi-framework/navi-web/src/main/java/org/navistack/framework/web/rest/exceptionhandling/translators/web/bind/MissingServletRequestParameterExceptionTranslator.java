package org.navistack.framework.web.rest.exceptionhandling.translators.web.bind;

import org.navistack.framework.core.error.UserErrorCodes;
import org.navistack.framework.web.rest.RestResult;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslation;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;

public class MissingServletRequestParameterExceptionTranslator implements ExceptionTranslator {
    @Override
    public ExceptionTranslation translate(Throwable throwable) {
        RestResult.ParameterizedError error = RestResult.ParameterizedError.of(
                UserErrorCodes.MISSING_PARAMETER,
                throwable.getMessage()
        );
        return ExceptionTranslation.of(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return MissingServletRequestParameterException.class.isAssignableFrom(throwableType);
    }
}
