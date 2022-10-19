package org.navistack.framework.web.rest.exceptionhandling.translators.locking;

import org.navistack.framework.web.rest.exceptionhandling.ExceptionHandling;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionHandlingConfigurer;

public class LockingExceptionHandlingConfigurer implements ExceptionHandlingConfigurer {
    @Override
    public void configure(ExceptionHandling exceptionHandling) {
        exceptionHandling.addTranslator(new LockAcquisitionFailureExceptionTranslator());
    }
}
