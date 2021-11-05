package org.navistack.framework.core.problem;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProblemBuilder {
    private String error;
    private String message;
    private final Map<String, Object> parameters = new LinkedHashMap<>();

    public ProblemBuilder withError(String error) {
        this.error = error;
        return this;
    }

    public ProblemBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public ProblemBuilder with(String key, Object value) {
        this.parameters.put(key, value);
        return this;
    }

    public ProblemBuilder with(Map<String, Object> parameters) {
        this.parameters.putAll(parameters);
        return this;
    }

    public Problem build() {
        return new GenericProblem(error, message, parameters);
    }
}
