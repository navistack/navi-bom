package org.navistack.framework.core.problem;

public class UncategorizedProblem extends Problem {
    public UncategorizedProblem(int error) {
        super(ProblemCodeBuilder.clientProblem(error));
    }

    public UncategorizedProblem(int error, String message) {
        super(ProblemCodeBuilder.clientProblem(error), message);
    }

    public UncategorizedProblem(int error, String message, Throwable cause) {
        super(ProblemCodeBuilder.clientProblem(error), message, cause);
    }

    public UncategorizedProblem(int error, Throwable cause) {
        super(ProblemCodeBuilder.clientProblem(error), cause);
    }

    public UncategorizedProblem(int error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ProblemCodeBuilder.clientProblem(error), message, cause, enableSuppression, writableStackTrace);
    }
}
