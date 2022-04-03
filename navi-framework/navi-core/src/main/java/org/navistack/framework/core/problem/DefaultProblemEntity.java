package org.navistack.framework.core.problem;

import java.util.Collections;
import java.util.Map;

public class DefaultProblemEntity implements ProblemEntity {
    private final int error;
    private final String message;
    private Map<String, Object> parameters = Collections.emptyMap();

    public DefaultProblemEntity(int error) {
        this.error = error;
        this.message = null;
    }

    public DefaultProblemEntity(int error, Map<String, Object> parameters) {
        this.error = error;
        this.message = null;
        this.parameters = parameters;
    }

    public DefaultProblemEntity(int error, String message) {
        this.error = error;
        this.message = message;
    }

    public DefaultProblemEntity(int error, String message, Map<String, Object> parameters) {
        this.error = error;
        this.message = message;
        this.parameters = parameters;
    }

    @Override
    public int getError() {
        return error;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Map<String, Object> getParameters() {
        return parameters;
    }
}
