package org.navistack.framework.core.error;

public class DomainException extends CodedException {
    public DomainException() {
        super();
    }

    public DomainException(int code) {
        super(ErrorCategory.Domain.errorCode(code));
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(int code, String message) {
        super(ErrorCategory.Domain.errorCode(code), message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainException(int code, String message, Throwable cause) {
        super(ErrorCategory.Domain.errorCode(code), message, cause);
    }

    public DomainException(Throwable cause) {
        super(cause);
    }

    public DomainException(int code, Throwable cause) {
        super(ErrorCategory.Domain.errorCode(code), cause);
    }

    protected DomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    protected DomainException(int code, String message, Throwable cause, boolean enableSuppression,
                              boolean writableStackTrace) {
        super(ErrorCategory.Domain.errorCode(code), message, cause, enableSuppression, writableStackTrace);
    }
}
