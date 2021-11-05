package org.navistack.framework.core.problem;

public class ThrowableBizProblem extends ThrowableProblem {
    public ThrowableBizProblem(String error) {
        super(error);
    }

    public ThrowableBizProblem(String error, String message) {
        super(error, message);
    }

    public ThrowableBizProblem(String error, String message, Throwable cause) {
        super(error, message, cause);
    }

    public ThrowableBizProblem(String error, Throwable cause) {
        super(error, cause);
    }

    public ThrowableBizProblem(String error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(error, message, cause, enableSuppression, writableStackTrace);
    }
}
