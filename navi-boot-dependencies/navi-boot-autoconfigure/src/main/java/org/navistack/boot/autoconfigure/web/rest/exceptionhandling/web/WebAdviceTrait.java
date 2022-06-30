package org.navistack.boot.autoconfigure.web.rest.exceptionhandling.web;

import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.web.bind.MethodArgumentNotValidAdviceTrait;
import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.web.bind.MissingServletRequestParameterAdviceTrait;
import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.web.servlet.NoHandlerFoundExceptionAdviceTrait;

public interface WebAdviceTrait extends
        MethodArgumentNotValidAdviceTrait,
        MissingServletRequestParameterAdviceTrait,
        NoHandlerFoundExceptionAdviceTrait,
        HttpRequestMethodNotSupportedExceptionAdviceTrait {
}
