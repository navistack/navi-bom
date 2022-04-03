package org.navistack.framework.core.problem;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExternalProblems {
    public final int GENERIC_FAILURE = ProblemCodeBuilder.externalProblem(0x000);

    public ExternalProblem genericFailure(String message) {
        return new ExternalProblem(GENERIC_FAILURE, message);
    }
}
