package org.navistack.framework.core.error;

public class DomainValidationException extends DomainException {
    private static final int ERROR_CODE = DomainErrorCodes.NO_SUCH_ENTITY;

    public DomainValidationException() {
        super(ERROR_CODE);
    }

    public DomainValidationException(String message) {
        super(ERROR_CODE, message);
    }

    public DomainValidationException(String message, Throwable cause) {
        super(ERROR_CODE, message, cause);
    }

    public DomainValidationException(Throwable cause) {
        super(ERROR_CODE, cause);
    }

    protected DomainValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ERROR_CODE, message, cause, enableSuppression, writableStackTrace);
    }
}
