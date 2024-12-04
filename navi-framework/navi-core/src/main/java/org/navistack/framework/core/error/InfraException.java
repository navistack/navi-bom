package org.navistack.framework.core.error;

public class InfraException extends CodedException {
    public InfraException() {
        super();
    }

    public InfraException(int code) {
        super(ErrorCategory.Infra.errorCode(code));
    }

    public InfraException(String message) {
        super(message);
    }

    public InfraException(int code, String message) {
        super(ErrorCategory.Infra.errorCode(code), message);
    }

    public InfraException(String message, Throwable cause) {
        super(message, cause);
    }

    public InfraException(int code, String message, Throwable cause) {
        super(ErrorCategory.Infra.errorCode(code), message, cause);
    }

    public InfraException(Throwable cause) {
        super(cause);
    }

    public InfraException(int code, Throwable cause) {
        super(ErrorCategory.Infra.errorCode(code), cause);
    }

    protected InfraException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    protected InfraException(int code, String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
        super(ErrorCategory.Infra.errorCode(code), message, cause, enableSuppression, writableStackTrace);
    }
}
