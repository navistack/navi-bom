package org.navistack.framework.core.problem;

public class SystemProblem extends Problem {
    public SystemProblem(int error) {
        super(ProblemCodeBuilder.systemProblem(error));
    }

    public SystemProblem(int error, String message) {
        super(ProblemCodeBuilder.systemProblem(error), message);
    }

    public SystemProblem(int error, String message, Throwable cause) {
        super(ProblemCodeBuilder.systemProblem(error), message, cause);
    }

    public SystemProblem(int error, Throwable cause) {
        super(ProblemCodeBuilder.systemProblem(error), cause);
    }

    public SystemProblem(int error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ProblemCodeBuilder.systemProblem(error), message, cause, enableSuppression, writableStackTrace);
    }
}
