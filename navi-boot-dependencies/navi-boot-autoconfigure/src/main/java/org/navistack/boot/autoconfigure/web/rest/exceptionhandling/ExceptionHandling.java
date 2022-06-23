package org.navistack.boot.autoconfigure.web.rest.exceptionhandling;

import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.general.GeneralAdviceTrait;
import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.validation.ValidationAdviceTrait;
import org.navistack.boot.autoconfigure.web.rest.exceptionhandling.web.WebAdviceTrait;

public interface ExceptionHandling extends
        GeneralAdviceTrait,
        ValidationAdviceTrait,
        WebAdviceTrait {
}
