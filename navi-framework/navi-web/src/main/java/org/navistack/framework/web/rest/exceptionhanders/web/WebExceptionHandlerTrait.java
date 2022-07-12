package org.navistack.framework.web.rest.exceptionhanders.web;

import org.navistack.framework.web.rest.exceptionhanders.web.bind.MethodArgumentNotValidExceptionHandlerTrait;
import org.navistack.framework.web.rest.exceptionhanders.web.bind.MissingServletRequestParameterExceptionHandlerTrait;
import org.navistack.framework.web.rest.exceptionhanders.web.servlet.NoHandlerFoundExceptionHandlerTrait;

public interface WebExceptionHandlerTrait extends
        MethodArgumentNotValidExceptionHandlerTrait,
        MissingServletRequestParameterExceptionHandlerTrait,
        NoHandlerFoundExceptionHandlerTrait,
        HttpRequestMethodNotSupportedExceptionHandlerTrait {
}
