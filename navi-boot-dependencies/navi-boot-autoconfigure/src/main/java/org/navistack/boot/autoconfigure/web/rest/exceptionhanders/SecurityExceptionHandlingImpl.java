package org.navistack.boot.autoconfigure.web.rest.exceptionhanders;

import lombok.Setter;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityExceptionHandlingImpl implements
        SecurityExceptionHandling {
    @Setter
    private boolean includeStackTrace;

    @Override
    public boolean includeStackTrace() {
        return includeStackTrace;
    }
}
