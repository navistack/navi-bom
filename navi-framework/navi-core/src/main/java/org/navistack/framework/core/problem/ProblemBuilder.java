package org.navistack.framework.core.problem;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProblemBuilder {
    private int error;
    private String message;
    private final Map<String, Object> parameters = new LinkedHashMap<>();
    private Throwable cause;

    public ProblemBuilder withError(int error) {
        this.error = error;
        return this;
    }

    public ProblemBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public ProblemBuilder withCause(Throwable cause) {
        this.cause = cause;
        return this;
    }

    public ProblemBuilder with(String key, Object value) {
        this.parameters.put(key, value);
        return this;
    }

    public ProblemEntity build() {
        return new Problem(error, message, parameters, cause);
    }
}
