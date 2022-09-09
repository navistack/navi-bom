package org.navistack.framework.web.rest.exceptionhanders;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingImpl extends AbstractExceptionTranslator implements
        ExceptionHandling {

}
