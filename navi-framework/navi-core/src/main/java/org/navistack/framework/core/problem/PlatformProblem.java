package org.navistack.framework.core.problem;

public class PlatformProblem extends Problem {
    public PlatformProblem(int error) {
        super(ProblemCodeBuilder.clientProblem(error));
    }

    public PlatformProblem(int error, String message) {
        super(ProblemCodeBuilder.platformProblem(error), message);
    }

    public PlatformProblem(int error, String message, Throwable cause) {
        super(ProblemCodeBuilder.platformProblem(error), message, cause);
    }

    public PlatformProblem(int error, Throwable cause) {
        super(ProblemCodeBuilder.platformProblem(error), cause);
    }

    public PlatformProblem(int error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ProblemCodeBuilder.platformProblem(error), message, cause, enableSuppression, writableStackTrace);
    }
}
