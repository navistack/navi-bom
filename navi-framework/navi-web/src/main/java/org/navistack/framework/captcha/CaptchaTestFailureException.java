package org.navistack.framework.captcha;

public class CaptchaTestFailureException extends RuntimeException{
    public CaptchaTestFailureException() {
        super();
    }

    public CaptchaTestFailureException(String message) {
        super(message);
    }

    public CaptchaTestFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaTestFailureException(Throwable cause) {
        super(cause);
    }

    protected CaptchaTestFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
