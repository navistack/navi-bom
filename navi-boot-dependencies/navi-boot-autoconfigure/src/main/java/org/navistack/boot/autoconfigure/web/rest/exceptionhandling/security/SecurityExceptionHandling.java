package org.navistack.boot.autoconfigure.web.rest.exceptionhandling.security;

import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.ExceptionHandling;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityExceptionHandling implements
        ExceptionHandling, SecurityAdviceTrait {
}
