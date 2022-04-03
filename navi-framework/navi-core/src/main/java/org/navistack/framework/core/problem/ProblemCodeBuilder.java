package org.navistack.framework.core.problem;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProblemCodeBuilder {
    private final int PROBLEM_BITS = 3 * 4;
    private final int PROBLEM_MASK = ~(-1 << PROBLEM_BITS);
    private final int PROBLEM_OFFSET = 0;

    private final int CATEGORY_BITS = 1 * 4;
    private final int CATEGORY_MASK = ~(-1 << CATEGORY_BITS);
    private final int CATEGORY_OFFSET = PROBLEM_OFFSET + CATEGORY_BITS;

    public int nonError() {
        return 0x0000;
    }

    public int problem(int category, int error) {
        return (category & CATEGORY_MASK) << CATEGORY_OFFSET
                | (error & PROBLEM_MASK) << PROBLEM_OFFSET
                ;
    }

    public int uncategorizedProblem(int error) {
        return problem(ProblemCategories.UNCATEGORIZED_PROBLEM, error);
    }

    public int platformProblem(int error) {
        return problem(ProblemCategories.PLATFORM_PROBLEM, error);
    }

    public int systemProblem(int error) {
        return problem(ProblemCategories.SYSTEM_PROBLEM, error);
    }

    public int domainProblem(int error) {
        return problem(ProblemCategories.DOMAIN_PROBLEM, error);
    }

    public int externalProblem(int error) {
        return problem(ProblemCategories.EXTERNAL_PROBLEM, error);
    }

    public int clientProblem(int error) {
        return problem(ProblemCategories.CLIENT_PROBLEM, error);
    }
}
