package org.navistack.framework.core.problem;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProblemEntityBuilder {
    private int error;
    private String message;
    private final Map<String, Object> parameters = new LinkedHashMap<>();

    public ProblemEntityBuilder withError(int error) {
        this.error = error;
        return this;
    }

    public ProblemEntityBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public ProblemEntityBuilder with(String key, Object value) {
        this.parameters.put(key, value);
        return this;
    }

    public ProblemEntityBuilder with(Map<String, Object> parameters) {
        this.parameters.putAll(parameters);
        return this;
    }

    public ProblemEntity build() {
        return new DefaultProblemEntity(error, message, parameters);
    }
}
