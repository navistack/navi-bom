package org.navistack.framework.core.problem;

import java.util.Collections;
import java.util.Map;

public interface Problem {
    String getError();

    String getMessage();

    default Map<String, Object> getParameters() {
        return Collections.emptyMap();
    }
}
