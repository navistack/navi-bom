package org.navistack.framework.web.rest.exceptionhandling.translators.captcha;

import org.navistack.framework.web.rest.exceptionhandling.ExceptionHandling;
import org.navistack.framework.web.rest.exceptionhandling.ExceptionHandlingConfigurer;

public class CaptchaExceptionHandlingConfigurer implements ExceptionHandlingConfigurer {
    @Override
    public void configure(ExceptionHandling exceptionHandling) {
        exceptionHandling.addTranslator(new CaptchaTestFailureExceptionTranslator());
    }
}
