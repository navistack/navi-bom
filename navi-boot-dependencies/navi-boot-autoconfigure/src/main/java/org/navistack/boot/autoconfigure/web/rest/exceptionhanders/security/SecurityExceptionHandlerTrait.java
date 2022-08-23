package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.security;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.security.access.AccessDeniedExceptionHandlerTrait;
import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.security.core.AuthenticationExceptionHandlerTrait;

public interface SecurityExceptionHandlerTrait extends
        AccessDeniedExceptionHandlerTrait,
        AuthenticationExceptionHandlerTrait {
}
