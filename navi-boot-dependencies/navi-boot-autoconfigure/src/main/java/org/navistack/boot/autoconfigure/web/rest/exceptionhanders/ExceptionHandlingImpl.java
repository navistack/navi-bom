package org.navistack.boot.autoconfigure.web.rest.exceptionhanders;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingImpl extends AbstractExceptionTranslator implements
        ExceptionHandling {

}
