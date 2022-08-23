package org.navistack.framework.core.error;

public final class UncategorizedException extends CodedException {
    public UncategorizedException() {
        super(ErrorCodeBuilder.uncategorizedError());
    }

    public UncategorizedException(String message) {
        super(ErrorCodeBuilder.uncategorizedError(), message);
    }

    public UncategorizedException(String message, Throwable cause) {
        super(ErrorCodeBuilder.uncategorizedError(), message, cause);
    }

    public UncategorizedException(Throwable cause) {
        super(ErrorCodeBuilder.uncategorizedError(), cause);
    }
}
