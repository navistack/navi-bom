package org.navistack.framework.web.rest.exceptionhandling.translators.web;

import org.navistack.framework.web.rest.exceptionhandling.ExceptionHandling;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionHandlingConfigurer;
import org.navistack.framework.web.rest.exceptionhandling.translators.web.bind.MissingServletRequestParameterExceptionTranslator;
import org.navistack.framework.web.rest.exceptionhandling.translators.web.servlet.NoHandlerFoundExceptionTranslator;

public class WebExceptionHandlingConfigurer implements ExceptionHandlingConfigurer {
    @Override
    public void configure(ExceptionHandling exceptionHandling) {
        exceptionHandling.addTranslator(new MissingServletRequestParameterExceptionTranslator());
        exceptionHandling.addTranslator(new NoHandlerFoundExceptionTranslator());
        exceptionHandling.addTranslator(new HttpRequestMethodNotSupportedExceptionTranslator());
    }
}
