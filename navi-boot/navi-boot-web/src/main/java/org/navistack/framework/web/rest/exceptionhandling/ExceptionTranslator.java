package org.navistack.framework.web.rest.exceptionhandling;

import org.navistack.framework.web.rest.RestErrResult;

public interface ExceptionTranslator {
    RestErrResult translate(Throwable throwable);

    boolean supports(Class<?> throwableType);
}
