package org.navistack.boot.autoconfigure.web.rest.exceptionhandling.security;

import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.security.access.AccessDeniedAdviceTrait;
import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.security.core.AuthenticationAdviceTrait;

public interface SecurityAdviceTrait extends
        AccessDeniedAdviceTrait,
        AuthenticationAdviceTrait {
}
