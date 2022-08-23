package org.navistack.framework.core.error;

public class SystemException extends CodedException {
    private final static int GENERAL_ERROR = 0x000;

    public SystemException() {
        super(ErrorCodeBuilder.systemError(GENERAL_ERROR));
    }

    public SystemException(int errorCode) {
        super(ErrorCodeBuilder.systemError(errorCode));
    }

    public SystemException(String message) {
        super(ErrorCodeBuilder.systemError(GENERAL_ERROR), message);
    }

    public SystemException(int errorCode, String message) {
        super(ErrorCodeBuilder.systemError(errorCode), message);
    }

    public SystemException(String message, Throwable cause) {
        super(ErrorCodeBuilder.systemError(GENERAL_ERROR), message, cause);
    }

    public SystemException(int errorCode, String message, Throwable cause) {
        super(ErrorCodeBuilder.systemError(errorCode), message, cause);
    }

    public SystemException(Throwable cause) {
        super(ErrorCodeBuilder.systemError(GENERAL_ERROR), cause);
    }

    public SystemException(int errorCode, Throwable cause) {
        super(ErrorCodeBuilder.systemError(errorCode), cause);
    }

    protected SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ErrorCodeBuilder.systemError(GENERAL_ERROR), message, cause, enableSuppression, writableStackTrace);
    }

    protected SystemException(int errorCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ErrorCodeBuilder.systemError(errorCode), message, cause, enableSuppression, writableStackTrace);
    }
}
