package org.navistack.framework.core.problem;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UncategorizedProblems {
    public final int GENERIC_FAILURE = ProblemCodeBuilder.uncategorizedProblem(0x000);
    public final int UNKNOWN_PROBLEM = ProblemCodeBuilder.uncategorizedProblem(0xFFF);

    public UncategorizedProblem genericFailure(String message) {
        return new UncategorizedProblem(GENERIC_FAILURE, message);
    }

    public UncategorizedProblem unknownFailure(String message) {
        return new UncategorizedProblem(UNKNOWN_PROBLEM, message);
    }
}
