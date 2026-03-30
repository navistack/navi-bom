package org.navistack.framework.web.rest.exceptionhandling.translators.security;

import org.navistack.framework.web.rest.exceptionhandling.ExceptionHandling;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionHandlingConfigurer;
import org.navistack.framework.web.rest.exceptionhandling.translators.security.access.AccessDeniedExceptionTranslator;
import org.navistack.framework.web.rest.exceptionhandling.translators.security.core.AuthenticationExceptionTranslator;

public class SecurityExceptionHandlingConfigurer implements ExceptionHandlingConfigurer {
    @Override
    public void configure(ExceptionHandling exceptionHandling) {
        exceptionHandling.addTranslator(new AccessDeniedExceptionTranslator());
        exceptionHandling.addTranslator(new AuthenticationExceptionTranslator());
    }
}
