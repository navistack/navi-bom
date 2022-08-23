package org.navistack.boot.autoconfigure.web.rest.exceptionhanders;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.core.CoreExceptionHandlerTrait;
import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.general.GeneralExceptionHandlerTrait;
import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.validation.ValidationExceptionHandlerTrait;
import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.web.WebExceptionHandlerTrait;

public interface ExceptionHandling extends
        CoreExceptionHandlerTrait,
        GeneralExceptionHandlerTrait,
        ValidationExceptionHandlerTrait,
        WebExceptionHandlerTrait {
}
