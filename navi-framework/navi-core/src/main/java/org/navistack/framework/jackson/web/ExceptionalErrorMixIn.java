package org.navistack.framework.jackson.web;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.navistack.framework.web.rest.ExceptionalEntity;

public interface ExceptionalErrorMixIn {
    String getError();

    String getMessage();

    @JsonUnwrapped
    ExceptionalEntity getException();
}
