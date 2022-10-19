package org.navistack.framework.web.rest.exceptionhandling.translators.validation;

import org.navistack.framework.core.error.UserErrorCodes;
import org.navistack.framework.web.rest.RestResult;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslation;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BindExceptionTranslator implements ExceptionTranslator {
    @Override
    public ExceptionTranslation translate(Throwable throwable) {
        BindException exception = (BindException) throwable;
        RestResult.ParameterizedError error = RestResult.ParameterizedError.of(
                UserErrorCodes.INVALID_PARAMETER,
                "Invalid Parameters",
                Collections.singletonMap("invalidParams", fromBindingResult(exception))
        );
        return ExceptionTranslation.of(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return BindException.class.isAssignableFrom(throwableType);
    }

    protected static InvalidParam fromFieldError(FieldError fieldError) {
        return InvalidParam.of(fieldError.getField(), fieldError.getDefaultMessage());
    }

    protected static InvalidParam fromObjectError(ObjectError objectError) {
        return InvalidParam.of(objectError.getObjectName(), objectError.getDefaultMessage());
    }

    protected static Collection<InvalidParam> fromBindingResult(BindingResult bindingResult) {
        return Stream.concat(
                bindingResult
                        .getFieldErrors()
                        .stream()
                        .map(BindExceptionTranslator::fromFieldError),
                bindingResult
                        .getGlobalErrors()
                        .stream()
                        .map(BindExceptionTranslator::fromObjectError)
        ).collect(Collectors.toList());
    }
}
