package org.navistack.framework.web.rest.exceptionhandling.translators.validation;

import org.navistack.framework.core.error.UserErrors;
import org.navistack.framework.web.rest.RestErrResult;
import org.navistack.framework.web.rest.RestResults;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionTranslator;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BindExceptionTranslator implements ExceptionTranslator {
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

    @Override
    public RestErrResult translate(Throwable throwable) {
        BindException exception = (BindException) throwable;
        return RestResults.err(throwable)
                .setError(UserErrors.INVALID_PARAMETER)
                .setMessage("Invalid Parameters")
                .setStatus(HttpStatus.BAD_REQUEST)
                .putParameter("invalidParams", fromBindingResult(exception));
    }

    @Override
    public boolean supports(Class<?> throwableType) {
        return BindException.class.isAssignableFrom(throwableType);
    }
}
