package org.navistack.boot.autoconfigure.web.rest;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.Map;

public interface ParameterizedErrorMixIn {
    String getError();

    String getMessage();

    @JsonAnyGetter
    Map<String, ? super Object> getParameters();
}
