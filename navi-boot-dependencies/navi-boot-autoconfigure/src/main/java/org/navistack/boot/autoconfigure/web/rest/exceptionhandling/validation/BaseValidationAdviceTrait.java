package org.navistack.boot.autoconfigure.web.rest.exceptionhandling.validation;

import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.common.AdviceTrait;
import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.InvalidParam;
import org.navistack.framework.core.problem.PlatformProblems;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface BaseValidationAdviceTrait extends AdviceTrait {
    default InvalidParam fromFieldError(FieldError fieldError) {
        return InvalidParam.of(fieldError.getField(), fieldError.getDefaultMessage());
    }

    default InvalidParam fromObjectError(ObjectError objectError) {
        return InvalidParam.of(objectError.getObjectName(), objectError.getDefaultMessage());
    }


    default Collection<InvalidParam> fromBindingResult(BindingResult bindingResult) {
        return Stream.concat(
                bindingResult
                        .getFieldErrors()
                        .stream()
                        .map(this::fromFieldError),
                bindingResult
                        .getGlobalErrors()
                        .stream()
                        .map(this::fromObjectError)
        ).collect(Collectors.toList());
    }

    default ResponseEntity<RestResult<Void, RestResult.ParameterizedError>> toResponse(
            Throwable throwable,
            Collection<InvalidParam> invalidParams) {
        return toResponse(
                throwable,
                RestResult.ParameterizedError.of(
                        PlatformProblems.INVALID_PARAMETER,
                        "Invalid Parameters",
                        Collections.singletonMap("invalidParams", invalidParams)
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
