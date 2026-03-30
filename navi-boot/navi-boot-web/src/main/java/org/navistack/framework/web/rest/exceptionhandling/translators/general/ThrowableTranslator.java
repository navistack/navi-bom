package org.navistack.framework.web.rest.exceptionhandling.translators.general;

import org.navistack.framework.web.rest.RestErrResult;
import org.navistack.framework.web.rest.RestResults;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;

public class ThrowableTranslator implements ExceptionTranslator {
    @Override
    public RestErrResult translate(Throwable throwable) {
        return RestResults.err(throwable);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return Throwable.class.isAssignableFrom(throwableType);
    }
}
