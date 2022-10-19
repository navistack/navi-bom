package org.navistack.framework.web.rest.exceptionhandling.translators.general;

import org.navistack.framework.core.error.UncategorizedErrorCodes;
import org.navistack.framework.web.rest.RestResult;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslation;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;

public class ThrowableTranslator implements ExceptionTranslator {
    @Override
    public ExceptionTranslation translate(Throwable throwable) {
        RestResult.ParameterizedError error = RestResult.ParameterizedError.of(
                UncategorizedErrorCodes.UNCATEGORIZED_ERROR,
                "Internal Server Error"
        );
        return ExceptionTranslation.of(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return Throwable.class.isAssignableFrom(throwableType);
    }
}
