package org.navistack.framework.web.rest.exceptionhandling.translators.core.error;

import org.navistack.framework.core.error.UserException;
import org.navistack.framework.web.rest.RestErrResult;
import org.navistack.framework.web.rest.RestResults;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;

public class UserExceptionTranslator implements ExceptionTranslator {
    @Override
    public RestErrResult translate(Throwable throwable) {
        return RestResults.err(throwable)
                .setStatus(HttpStatus.BAD_REQUEST);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return UserException.class.isAssignableFrom(throwableType);
    }
}
