package org.navistack.framework.jackson.web;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.Map;

public interface ParameterizedErrorMixIn {
    String getError();

    String getMessage();

    @JsonAnyGetter
    Map<String, ? super Object> getParameters();
}
