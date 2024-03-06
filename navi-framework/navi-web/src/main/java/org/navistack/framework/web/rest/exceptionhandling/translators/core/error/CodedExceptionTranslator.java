package org.navistack.framework.web.rest.exceptionhandling.translators.core.error;

import org.navistack.framework.core.error.CodedException;
import org.navistack.framework.web.rest.RestErrResult;
import org.navistack.framework.web.rest.RestResults;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;

public class CodedExceptionTranslator implements ExceptionTranslator {
    @Override
    public RestErrResult translate(Throwable throwable) {
        return RestResults.err(throwable)
                .setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return CodedException.class.isAssignableFrom(throwableType);
    }
}
