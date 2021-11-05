package org.navistack.framework.core.problem;

public class ThrowableUnexpectedProblem extends ThrowableProblem {
    public ThrowableUnexpectedProblem(String error) {
        super(error);
    }

    public ThrowableUnexpectedProblem(String error, String message) {
        super(error, message);
    }

    public ThrowableUnexpectedProblem(String error, String message, Throwable cause) {
        super(error, message, cause);
    }

    public ThrowableUnexpectedProblem(String error, Throwable cause) {
        super(error, cause);
    }

    public ThrowableUnexpectedProblem(String error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(error, message, cause, enableSuppression, writableStackTrace);
    }
}
