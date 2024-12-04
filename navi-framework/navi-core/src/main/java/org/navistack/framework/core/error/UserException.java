package org.navistack.framework.core.error;

public class UserException extends CodedException {
    public UserException() {
        super();
    }

    public UserException(int code) {
        super(ErrorCategory.User.errorCode(code));
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(int code, String message) {
        super(ErrorCategory.User.errorCode(code), message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(int code, String message, Throwable cause) {
        super(ErrorCategory.User.errorCode(code), message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    public UserException(int code, Throwable cause) {
        super(ErrorCategory.User.errorCode(code), cause);
    }

    protected UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    protected UserException(int code, String message, Throwable cause, boolean enableSuppression,
                            boolean writableStackTrace) {
        super(ErrorCategory.User.errorCode(code), message, cause, enableSuppression, writableStackTrace);
    }
}
