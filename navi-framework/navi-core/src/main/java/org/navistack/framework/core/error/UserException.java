package org.navistack.framework.core.error;

public class UserException extends CodedException {
    @Override
    public int getErrorCode() {
        return super.getErrorCode();
    }

    public UserException(int errorCode) {
        super(errorCode);
    }

    public UserException(int errorCode, String message) {
        super(errorCode, message);
    }

    public UserException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public UserException(int errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    protected UserException(int errorCode, String message, Throwable cause, boolean enableSuppression,
                            boolean writableStackTrace) {
        super(errorCode, message, cause, enableSuppression, writableStackTrace);
    }
}
