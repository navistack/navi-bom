package org.navistack.framework.core.problem;

import java.util.LinkedHashMap;
import java.util.Map;

public class ThrowableProblemBuilder {
    private String error;
    private String message;
    private final Map<String, Object> parameters = new LinkedHashMap<>();
    private Throwable cause;

    public ThrowableProblemBuilder withError(String error) {
        this.error = error;
        return this;
    }

    public ThrowableProblemBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public ThrowableProblemBuilder withCause(Throwable cause) {
        this.cause = cause;
        return this;
    }

    public ThrowableProblemBuilder with(String key, Object value) {
        this.parameters.put(key, value);
        return this;
    }

    public Problem build() {
        return new GenericThrowableProblem(error, message, parameters, cause);
    }
}
