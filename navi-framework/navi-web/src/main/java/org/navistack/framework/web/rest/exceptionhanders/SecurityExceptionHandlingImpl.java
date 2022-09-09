package org.navistack.framework.web.rest.exceptionhanders;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityExceptionHandlingImpl extends AbstractExceptionTranslator implements
        SecurityExceptionHandling {

}
