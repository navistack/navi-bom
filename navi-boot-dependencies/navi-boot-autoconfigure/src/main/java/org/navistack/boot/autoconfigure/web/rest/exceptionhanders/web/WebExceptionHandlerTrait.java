package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.web;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.web.bind.MethodArgumentNotValidExceptionHandlerTrait;
import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.web.bind.MissingServletRequestParameterExceptionHandlerTrait;
import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.web.servlet.NoHandlerFoundExceptionHandlerTrait;

public interface WebExceptionHandlerTrait extends
        MethodArgumentNotValidExceptionHandlerTrait,
        MissingServletRequestParameterExceptionHandlerTrait,
        NoHandlerFoundExceptionHandlerTrait,
        HttpRequestMethodNotSupportedExceptionHandlerTrait {
}
