package org.navistack.framework.jackson.web;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.navistack.framework.web.rest.ExceptionalEntity;

import java.util.Map;

public interface ExceptionalErrorMixIn {
    String getError();

    String getMessage();

    @JsonAnyGetter
    Map<String, ? super Object> getParameters();

    @JsonUnwrapped
    ExceptionalEntity getException();
}
