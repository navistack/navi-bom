package org.navistack.framework.web.rest.exceptionhanders;

import org.navistack.framework.web.rest.exceptionhanders.core.CoreExceptionHandlerTrait;
import org.navistack.framework.web.rest.exceptionhanders.general.GeneralExceptionHandlerTrait;
import org.navistack.framework.web.rest.exceptionhanders.validation.ValidationExceptionHandlerTrait;
import org.navistack.framework.web.rest.exceptionhanders.web.WebExceptionHandlerTrait;

public interface ExceptionHandling extends
        CoreExceptionHandlerTrait,
        GeneralExceptionHandlerTrait,
        ValidationExceptionHandlerTrait,
        WebExceptionHandlerTrait {
}
