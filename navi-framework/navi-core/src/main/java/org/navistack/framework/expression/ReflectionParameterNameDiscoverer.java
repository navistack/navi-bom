package org.navistack.framework.expression;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionParameterNameDiscoverer implements ParameterNameDiscoverer {
    @Override
    public String[] getParameterNames(Method method) {
        return Arrays.stream(method.getParameters())
                .map(java.lang.reflect.Parameter::getName)
                .toArray(String[]::new);
    }
}
