package org.navistack.framework.core.error;

public class UserException extends CodedException {
    private static final int GENERAL_ERROR = 0x000;

    public UserException() {
        super(ErrorCodeBuilder.userError(GENERAL_ERROR));
    }

    public UserException(int errorCode) {
        super(ErrorCodeBuilder.userError(errorCode));
    }

    public UserException(String message) {
        super(ErrorCodeBuilder.userError(GENERAL_ERROR), message);
    }

    public UserException(int errorCode, String message) {
        super(ErrorCodeBuilder.userError(errorCode), message);
    }

    public UserException(String message, Throwable cause) {
        super(ErrorCodeBuilder.userError(GENERAL_ERROR), message, cause);
    }

    public UserException(int errorCode, String message, Throwable cause) {
        super(ErrorCodeBuilder.userError(errorCode), message, cause);
    }

    public UserException(Throwable cause) {
        super(ErrorCodeBuilder.userError(GENERAL_ERROR), cause);
    }

    public UserException(int errorCode, Throwable cause) {
        super(ErrorCodeBuilder.userError(errorCode), cause);
    }

    protected UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ErrorCodeBuilder.userError(GENERAL_ERROR), message, cause, enableSuppression, writableStackTrace);
    }

    protected UserException(int errorCode,
                            String message,
                            Throwable cause,
                            boolean enableSuppression,
                            boolean writableStackTrace) {
        super(ErrorCodeBuilder.userError(errorCode), message, cause, enableSuppression, writableStackTrace);
    }
}
