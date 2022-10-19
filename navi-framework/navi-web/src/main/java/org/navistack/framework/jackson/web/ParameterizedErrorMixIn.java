package org.navistack.framework.jackson.web;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

public interface ParameterizedErrorMixIn {
    String getError();

    String getMessage();

    @JsonAnyGetter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Map<String, ? super Object> getParameters();
}
