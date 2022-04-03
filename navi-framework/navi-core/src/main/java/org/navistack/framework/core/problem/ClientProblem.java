package org.navistack.framework.core.problem;

public class ClientProblem extends Problem {
    public ClientProblem(int error) {
        super(ProblemCodeBuilder.clientProblem(error));
    }

    public ClientProblem(int error, String message) {
        super(ProblemCodeBuilder.clientProblem(error), message);
    }

    public ClientProblem(int error, String message, Throwable cause) {
        super(ProblemCodeBuilder.clientProblem(error), message, cause);
    }

    public ClientProblem(int error, Throwable cause) {
        super(ProblemCodeBuilder.clientProblem(error), cause);
    }

    public ClientProblem(int error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ProblemCodeBuilder.clientProblem(error), message, cause, enableSuppression, writableStackTrace);
    }
}
