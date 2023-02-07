package org.navistack.framework.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;

public class ExceptionHandlingHttpSecuritySupport implements AuthenticationEntryPoint, AccessDeniedHandler {

    private final HandlerExceptionResolver resolver;

    public ExceptionHandlingHttpSecuritySupport(final HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response,
                         final AuthenticationException exception) {
        resolver.resolveException(request, response, null, exception);
    }

    @Override
    public void handle(final HttpServletRequest request, final HttpServletResponse response,
                       final AccessDeniedException exception) {
        resolver.resolveException(request, response, null, exception);
    }

}
