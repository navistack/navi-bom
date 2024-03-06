package org.navistack.framework.web.rest.exceptionhandling.translators.locking;

import org.navistack.framework.core.error.UserErrors;
import org.navistack.framework.locking.LockAcquisitionFailureException;
import org.navistack.framework.web.rest.RestErrResult;
import org.navistack.framework.web.rest.RestResults;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;

public class LockAcquisitionFailureExceptionTranslator implements ExceptionTranslator {
    @Override
    public RestErrResult translate(Throwable throwable) {
        return RestResults.err(throwable)
                .setError(UserErrors.RESOURCE_LOCKED)
                .setStatus(HttpStatus.CONFLICT);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return LockAcquisitionFailureException.class.isAssignableFrom(throwableType);
    }
}
