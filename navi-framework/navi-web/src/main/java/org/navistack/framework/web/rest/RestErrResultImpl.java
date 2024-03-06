package org.navistack.framework.web.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class RestErrResultImpl implements RestErrResult {
    private int error = -1;

    @NonNull
    private String message = "Internal Server Error";

    private String endpoint;

    @NonNull
    private Map<String, Object> parameters = new HashMap<>();

    @NonNull
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    private Throwable throwable;

    public Object getParameter(String key) {
        return this.parameters.get(key);
    }

    public Object getParameter(String key, Object defaultValue) {
        return this.parameters.getOrDefault(key, defaultValue);
    }

    public RestErrResultImpl putParameter(String key, Object value) {
        this.parameters.put(key, value);
        return this;
    }

    @Override
    public RestErrResult putParameters(Map<String, Object> parameters) {
        this.parameters.putAll(parameters);
        return this;
    }
}
