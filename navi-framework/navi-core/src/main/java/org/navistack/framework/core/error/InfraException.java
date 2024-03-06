package org.navistack.framework.core.error;

public class InfraException extends CodedException {
    @Override
    public int getErrorCode() {
        return super.getErrorCode();
    }

    public InfraException(int errorCode) {
        super(errorCode);
    }

    public InfraException(int errorCode, String message) {
        super(errorCode, message);
    }

    public InfraException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public InfraException(int errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    protected InfraException(int errorCode, String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
        super(errorCode, message, cause, enableSuppression, writableStackTrace);
    }
}
