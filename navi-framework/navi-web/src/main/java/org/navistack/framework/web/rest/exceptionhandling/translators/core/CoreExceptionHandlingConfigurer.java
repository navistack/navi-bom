package org.navistack.framework.web.rest.exceptionhandling.translators.core;

import org.navistack.framework.web.rest.exceptionhandling.ExceptionHandling;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionHandlingConfigurer;
import org.navistack.framework.web.rest.exceptionhandling.translators.core.error.CodedExceptionTranslator;
import org.navistack.framework.web.rest.exceptionhandling.translators.core.error.UserExceptionTranslator;

public class CoreExceptionHandlingConfigurer implements ExceptionHandlingConfigurer {
    @Override
    public void configure(ExceptionHandling exceptionHandling) {
        exceptionHandling.addTranslator(new UserExceptionTranslator());
        exceptionHandling.addTranslator(new CodedExceptionTranslator());
    }
}
