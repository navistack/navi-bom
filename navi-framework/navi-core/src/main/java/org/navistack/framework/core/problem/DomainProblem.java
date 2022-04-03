package org.navistack.framework.core.problem;

public class DomainProblem extends Problem {
    public DomainProblem(int error) {
        super(ProblemCodeBuilder.domainProblem(error));
    }

    public DomainProblem(int error, String message) {
        super(ProblemCodeBuilder.domainProblem(error), message);
    }

    public DomainProblem(int error, String message, Throwable cause) {
        super(ProblemCodeBuilder.domainProblem(error), message, cause);
    }

    public DomainProblem(int error, Throwable cause) {
        super(ProblemCodeBuilder.domainProblem(error), cause);
    }

    public DomainProblem(int error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ProblemCodeBuilder.domainProblem(error), message, cause, enableSuppression, writableStackTrace);
    }
}
