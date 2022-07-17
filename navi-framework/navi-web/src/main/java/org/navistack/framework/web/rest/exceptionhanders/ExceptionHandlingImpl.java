package org.navistack.framework.web.rest.exceptionhanders;

import lombok.Setter;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingImpl implements
        ExceptionHandling {
    @Setter
    private boolean includeStackTrace;

    @Override
    public boolean includeStackTrace() {
        return includeStackTrace;
    }
}
