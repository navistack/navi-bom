package org.navistack.framework.core.problem;

import java.util.Collections;
import java.util.Map;

public class GenericThrowableProblem extends ThrowableProblem {
    private final Map<String, Object> parameters;

    public GenericThrowableProblem(String error) {
        super(error);
        this.parameters = Collections.emptyMap();
    }

    public GenericThrowableProblem(String error, Map<String, Object> parameters) {
        super(error);
        this.parameters = parameters;
    }

    public GenericThrowableProblem(String error, String message) {
        super(error, message);
        this.parameters = Collections.emptyMap();
    }

    public GenericThrowableProblem(String error, String message, Map<String, Object> parameters) {
        super(error, message);
        this.parameters = parameters;
    }

    public GenericThrowableProblem(String error, String message, Throwable cause) {
        super(error, message, cause);
        this.parameters = Collections.emptyMap();
    }

    public GenericThrowableProblem(String error, String message, Map<String, Object> parameters, Throwable cause) {
        super(error, message, cause);
        this.parameters = parameters;
    }

    public GenericThrowableProblem(String error, Throwable cause) {
        super(error, cause);
        this.parameters = Collections.emptyMap();
    }

    public GenericThrowableProblem(String error, Map<String, Object> parameters, Throwable cause) {
        super(error, cause);
        this.parameters = parameters;
    }

    public GenericThrowableProblem(String error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(error, message, cause, enableSuppression, writableStackTrace);
        this.parameters = Collections.emptyMap();
    }

    public GenericThrowableProblem(String error, String message, Map<String, Object> parameters, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(error, message, cause, enableSuppression, writableStackTrace);
        this.parameters = parameters;
    }

    @Override
    public Map<String, Object> getParameters() {
        return parameters;
    }
}
