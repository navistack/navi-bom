package org.navistack.framework.web.rest.exceptionhandling.translators.web.bind;

import org.navistack.framework.core.error.UserErrors;
import org.navistack.framework.web.rest.RestErrResult;
import org.navistack.framework.web.rest.RestResults;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;

public class MissingServletRequestParameterExceptionTranslator implements ExceptionTranslator {
    @Override
    public RestErrResult translate(Throwable throwable) {
        return RestResults.err(throwable)
                .setError(UserErrors.MISSING_PARAMETER)
                .setStatus(HttpStatus.BAD_REQUEST);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return MissingServletRequestParameterException.class.isAssignableFrom(throwableType);
    }
}
