package org.navistack.framework.web.rest.exceptionhandling.translators.core.ratelimit;

import org.navistack.framework.core.error.UserErrorCodes;
import org.navistack.framework.ratelimit.RateLimitExceededException;
import org.navistack.framework.web.rest.RestResult;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslation;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;

public class RateLimitExceededExceptionTranslator implements ExceptionTranslator {
    @Override
    public ExceptionTranslation translate(Throwable throwable) {
        RestResult.ParameterizedError error = RestResult.ParameterizedError.of(
                UserErrorCodes.FREQUENT_REQUEST,
                throwable.getMessage()
        );
        return ExceptionTranslation.of(error, HttpStatus.TOO_MANY_REQUESTS);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return RateLimitExceededException.class.isAssignableFrom(throwableType);
    }
}
