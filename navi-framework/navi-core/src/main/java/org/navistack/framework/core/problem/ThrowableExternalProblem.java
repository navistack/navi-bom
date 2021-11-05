package org.navistack.framework.core.problem;

public class ThrowableExternalProblem extends ThrowableProblem {
    public ThrowableExternalProblem(String error) {
        super(error);
    }

    public ThrowableExternalProblem(String error, String message) {
        super(error, message);
    }

    public ThrowableExternalProblem(String error, String message, Throwable cause) {
        super(error, message, cause);
    }

    public ThrowableExternalProblem(String error, Throwable cause) {
        super(error, cause);
    }

    public ThrowableExternalProblem(String error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(error, message, cause, enableSuppression, writableStackTrace);
    }
}
