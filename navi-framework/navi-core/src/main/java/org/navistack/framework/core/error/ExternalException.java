package org.navistack.framework.core.error;

public class ExternalException extends CodedException {
    private final static int GENERAL_ERROR = 0x000;

    public ExternalException() {
        super(ErrorCodeBuilder.externalError(GENERAL_ERROR));
    }

    public ExternalException(int errorCode) {
        super(ErrorCodeBuilder.externalError(errorCode));
    }

    public ExternalException(String message) {
        super(ErrorCodeBuilder.externalError(GENERAL_ERROR), message);
    }

    public ExternalException(int errorCode, String message) {
        super(ErrorCodeBuilder.externalError(errorCode), message);
    }

    public ExternalException(String message, Throwable cause) {
        super(ErrorCodeBuilder.externalError(GENERAL_ERROR), message, cause);
    }

    public ExternalException(int errorCode, String message, Throwable cause) {
        super(ErrorCodeBuilder.externalError(errorCode), message, cause);
    }

    public ExternalException(Throwable cause) {
        super(ErrorCodeBuilder.externalError(GENERAL_ERROR), cause);
    }

    public ExternalException(int errorCode, Throwable cause) {
        super(ErrorCodeBuilder.externalError(errorCode), cause);
    }

    protected ExternalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ErrorCodeBuilder.externalError(GENERAL_ERROR), message, cause, enableSuppression, writableStackTrace);
    }

    protected ExternalException(int errorCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ErrorCodeBuilder.externalError(errorCode), message, cause, enableSuppression, writableStackTrace);
    }
}
