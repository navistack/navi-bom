package org.navistack.framework.core.error;

public class DomainException extends CodedException {
    private final static int GENERAL_ERROR = 0x000;

    public DomainException() {
        super(ErrorCodeBuilder.domainError(GENERAL_ERROR));
    }

    public DomainException(int errorCode) {
        super(ErrorCodeBuilder.domainError(errorCode));
    }

    public DomainException(String message) {
        super(ErrorCodeBuilder.domainError(GENERAL_ERROR), message);
    }

    public DomainException(int errorCode, String message) {
        super(ErrorCodeBuilder.domainError(errorCode), message);
    }

    public DomainException(String message, Throwable cause) {
        super(ErrorCodeBuilder.domainError(GENERAL_ERROR), message, cause);
    }

    public DomainException(int errorCode, String message, Throwable cause) {
        super(ErrorCodeBuilder.domainError(errorCode), message, cause);
    }

    public DomainException(Throwable cause) {
        super(ErrorCodeBuilder.domainError(GENERAL_ERROR), cause);
    }

    public DomainException(int errorCode, Throwable cause) {
        super(ErrorCodeBuilder.domainError(errorCode), cause);
    }

    protected DomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ErrorCodeBuilder.domainError(GENERAL_ERROR), message, cause, enableSuppression, writableStackTrace);
    }

    protected DomainException(int errorCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ErrorCodeBuilder.domainError(errorCode), message, cause, enableSuppression, writableStackTrace);
    }
}
