package org.navistack.framework.web.rest.exceptionhanders.security;

import org.navistack.framework.web.rest.exceptionhanders.security.access.AccessDeniedExceptionHandlerTrait;
import org.navistack.framework.web.rest.exceptionhanders.security.core.AuthenticationExceptionHandlerTrait;

public interface SecurityExceptionHandlerTrait extends
        AccessDeniedExceptionHandlerTrait,
        AuthenticationExceptionHandlerTrait {
}
