package org.navistack.framework.core.problem;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientProblems {
    public final int GENERIC_FAILURE = ProblemCodeBuilder.clientProblem(0x000);

    public ClientProblem genericFailure(String message) {
        return new ClientProblem(GENERIC_FAILURE, message);
    }
}
