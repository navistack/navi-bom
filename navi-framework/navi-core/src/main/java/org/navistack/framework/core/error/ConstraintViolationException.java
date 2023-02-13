package org.navistack.framework.core.error;

public class ConstraintViolationException extends DomainException {
    private static final int ERROR_CODE = DomainErrorCodes.CONSTRAINT_VIOLATION;

    public ConstraintViolationException() {
        super(ERROR_CODE);
    }

    public ConstraintViolationException(String message) {
        super(ERROR_CODE, message);
    }

    public ConstraintViolationException(String message, Throwable cause) {
        super(ERROR_CODE, message, cause);
    }

    public ConstraintViolationException(Throwable cause) {
        super(ERROR_CODE, cause);
    }

    protected ConstraintViolationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ERROR_CODE, message, cause, enableSuppression, writableStackTrace);
    }
}
