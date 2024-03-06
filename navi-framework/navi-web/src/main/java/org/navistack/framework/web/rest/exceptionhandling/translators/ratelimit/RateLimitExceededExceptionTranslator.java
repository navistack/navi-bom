package org.navistack.framework.web.rest.exceptionhandling.translators.ratelimit;

import org.navistack.framework.core.error.UserErrors;
import org.navistack.framework.ratelimit.RateLimitExceededException;
import org.navistack.framework.web.rest.RestErrResult;
import org.navistack.framework.web.rest.RestResults;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;

public class RateLimitExceededExceptionTranslator implements ExceptionTranslator {
    @Override
    public RestErrResult translate(Throwable throwable) {
        return RestResults.err(throwable)
                .setError(UserErrors.FREQUENT_REQUEST)
                .setStatus(HttpStatus.TOO_MANY_REQUESTS);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return RateLimitExceededException.class.isAssignableFrom(throwableType);
    }
}
