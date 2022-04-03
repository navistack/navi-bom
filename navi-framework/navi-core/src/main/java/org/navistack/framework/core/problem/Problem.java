package org.navistack.framework.core.problem;

import java.util.Collections;
import java.util.Map;

public class Problem extends RuntimeException implements ProblemEntity {
    private final int error;
    private final Map<String, Object> parameters;

    public Problem(int error) {
        super();
        this.error = error;
        this.parameters = Collections.emptyMap();
    }

    public Problem(int error, Map<String, Object> parameters) {
        super();
        this.error = error;
        this.parameters = parameters;
    }

    public Problem(int error, String message) {
        super(message);
        this.error = error;
        this.parameters = Collections.emptyMap();
    }

    public Problem(int error, String message, Map<String, Object> parameters) {
        super(message);
        this.error = error;
        this.parameters = parameters;
    }

    public Problem(int error, String message, Throwable cause) {
        super(message, cause);
        this.error = error;
        this.parameters = Collections.emptyMap();
    }

    public Problem(int error, String message, Map<String, Object> parameters, Throwable cause) {
        super(message, cause);
        this.error = error;
        this.parameters = parameters;
    }

    public Problem(int error, Throwable cause) {
        super(cause);
        this.error = error;
        this.parameters = Collections.emptyMap();
    }

    public Problem(int error, Map<String, Object> parameters, Throwable cause) {
        super(cause);
        this.error = error;
        this.parameters = parameters;
    }

    public Problem(int error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
        this.parameters = Collections.emptyMap();
    }

    public Problem(int error, String message, Map<String, Object> parameters, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
        this.parameters = parameters;
    }

    @Override
    public int getError() {
        return error;
    }

    @Override
    public Map<String, Object> getParameters() {
        return parameters;
    }
}
