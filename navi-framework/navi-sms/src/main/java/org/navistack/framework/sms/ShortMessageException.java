package org.navistack.framework.sms;

public class ShortMessageException extends RuntimeException {
    public ShortMessageException() {
        super();
    }

    public ShortMessageException(String message) {
        super(message);
    }

    public ShortMessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShortMessageException(Throwable cause) {
        super(cause);
    }

    protected ShortMessageException(String message,
                                    Throwable cause,
                                    boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
