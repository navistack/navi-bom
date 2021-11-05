package org.navistack.framework.core.problem;

import java.util.Collections;
import java.util.Map;

public class GenericProblem implements Problem {
    private final String error;
    private final String message;
    private Map<String, Object> parameters = Collections.emptyMap();

    public GenericProblem(String error) {
        this.error = error;
        this.message = null;
    }

    public GenericProblem(String error, Map<String, Object> parameters) {
        this.error = error;
        this.message = null;
        this.parameters = parameters;
    }

    public GenericProblem(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public GenericProblem(String error, String message, Map<String, Object> parameters) {
        this.error = error;
        this.message = message;
        this.parameters = parameters;
    }

    @Override
    public String getError() {
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
