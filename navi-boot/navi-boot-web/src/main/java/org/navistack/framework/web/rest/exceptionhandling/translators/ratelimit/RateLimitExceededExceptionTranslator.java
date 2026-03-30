package org.navistack.framework.web.rest.exceptionhandling.translators.ratelimit;

import org.navistack.framework.core.error.ErrorCodes;
import org.navistack.framework.ratelimit.RateLimitExceededException;
import org.navistack.framework.web.rest.RestErrResult;
import org.navistack.framework.web.rest.RestResults;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;

public class RateLimitExceededExceptionTranslator implements ExceptionTranslator {
    @Override
    public RestErrResult translate(Throwable throwable) {
        return RestResults.err(throwable)
                .setError(ErrorCodes.FREQUENT_REQUEST)
                .setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return RateLimitExceededException.class.isAssignableFrom(throwableType);
    }
}
