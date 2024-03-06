package org.navistack.framework.core.error;

public class AppException extends CodedException {
    @Override
    public int getErrorCode() {
        return super.getErrorCode();
    }

    public AppException(int errorCode) {
        super(errorCode);
    }

    public AppException(int errorCode, String message) {
        super(errorCode, message);
    }

    public AppException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public AppException(int errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    protected AppException(int errorCode, String message, Throwable cause, boolean enableSuppression,
                           boolean writableStackTrace) {
        super(errorCode, message, cause, enableSuppression, writableStackTrace);
    }
}
