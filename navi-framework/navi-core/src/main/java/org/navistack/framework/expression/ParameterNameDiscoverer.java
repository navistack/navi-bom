package org.navistack.framework.expression;

import java.lang.reflect.Method;

public interface ParameterNameDiscoverer {
    String[] getParameterNames(Method method);
}
