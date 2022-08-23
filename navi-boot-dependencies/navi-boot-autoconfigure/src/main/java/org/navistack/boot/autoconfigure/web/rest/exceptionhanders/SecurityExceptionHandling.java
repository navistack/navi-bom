package org.navistack.boot.autoconfigure.web.rest.exceptionhanders;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.security.SecurityExceptionHandlerTrait;

public interface SecurityExceptionHandling extends
        ExceptionHandling, SecurityExceptionHandlerTrait {
}
