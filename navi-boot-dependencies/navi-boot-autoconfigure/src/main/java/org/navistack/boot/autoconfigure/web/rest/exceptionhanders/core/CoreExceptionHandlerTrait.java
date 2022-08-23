package org.navistack.boot.autoconfigure.web.rest.exceptionhanders.core;

import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.core.captcha.CaptchaTestFailureExceptionHandlerTrait;
import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.core.domain.DomainExceptionHandlerTrait;
import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.core.error.CodedExceptionHandlerTrait;
import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.core.error.UserExceptionHandlerTrait;
import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.core.locking.LockAcquisitionFailureExceptionHandlerTrait;
import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.core.ratelimit.RateLimitExceededExceptionHandlerTrait;

public interface CoreExceptionHandlerTrait extends
        CaptchaTestFailureExceptionHandlerTrait,
        DomainExceptionHandlerTrait,
        CodedExceptionHandlerTrait,
        UserExceptionHandlerTrait,
        LockAcquisitionFailureExceptionHandlerTrait,
        RateLimitExceededExceptionHandlerTrait {
}
