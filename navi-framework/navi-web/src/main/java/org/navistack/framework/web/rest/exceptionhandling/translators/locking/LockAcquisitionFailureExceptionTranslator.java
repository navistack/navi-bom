package org.navistack.framework.web.rest.exceptionhandling.translators.locking;

import org.navistack.framework.core.error.UserErrorCodes;
import org.navistack.framework.locking.LockAcquisitionFailureException;
import org.navistack.framework.web.rest.RestResult;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslation;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;

public class LockAcquisitionFailureExceptionTranslator implements ExceptionTranslator {
    @Override
    public ExceptionTranslation translate(Throwable throwable) {
        RestResult.ParameterizedError error = RestResult.ParameterizedError.of(
                UserErrorCodes.RESOURCE_LOCKED,
                throwable.getMessage()
        );
        return ExceptionTranslation.of(error, HttpStatus.CONFLICT);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return LockAcquisitionFailureException.class.isAssignableFrom(throwableType);
    }
}
