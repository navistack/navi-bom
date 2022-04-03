package org.navistack.framework.core.problem;

public class ExternalProblem extends Problem {
    public ExternalProblem(int error) {
        super(ProblemCodeBuilder.externalProblem(error));
    }

    public ExternalProblem(int error, String message) {
        super(ProblemCodeBuilder.externalProblem(error), message);
    }

    public ExternalProblem(int error, String message, Throwable cause) {
        super(ProblemCodeBuilder.externalProblem(error), message, cause);
    }

    public ExternalProblem(int error, Throwable cause) {
        super(ProblemCodeBuilder.externalProblem(error), cause);
    }

    public ExternalProblem(int error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ProblemCodeBuilder.externalProblem(error), message, cause, enableSuppression, writableStackTrace);
    }
}
