package org.navistack.framework.web.rest.exceptionhandling;

public interface ExceptionTranslator {
    ExceptionTranslation translate(Throwable throwable);

    boolean supports(Class<?> throwableType);
}
