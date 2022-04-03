package org.navistack.framework.core.problem;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DomainProblems {
    public final int GENERIC_FAILURE = ProblemCodeBuilder.domainProblem(0x000);
    public final int ENTITY_DUPLICATED = ProblemCodeBuilder.domainProblem(0x001);

    public DomainProblem genericFailure(String message) {
        return new DomainProblem(GENERIC_FAILURE, message);
    }

    public DomainProblem entityDuplicated(String message) {
        return new DomainProblem(ENTITY_DUPLICATED, message);
    }
}
