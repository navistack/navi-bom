package org.navistack.framework.core.problem;

public class ThrowableProblem extends RuntimeException implements Problem {
    private final String error;

    public ThrowableProblem(String error) {
        super();
        this.error = error;
    }

    public ThrowableProblem(String error, String message) {
        super(message);
        this.error = error;
    }

    public ThrowableProblem(String error, String message, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public ThrowableProblem(String error, Throwable cause) {
        super(cause);
        this.error = error;
    }

    public ThrowableProblem(String error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
    }

    @Override
    public String getError() {
        return error;
    }
}
