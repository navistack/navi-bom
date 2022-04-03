package org.navistack.framework.core.problem;

import java.util.Collections;
import java.util.Map;

public interface ProblemEntity {
    int getError();

    String getMessage();

    default Map<String, Object> getParameters() {
        return Collections.emptyMap();
    }
}
