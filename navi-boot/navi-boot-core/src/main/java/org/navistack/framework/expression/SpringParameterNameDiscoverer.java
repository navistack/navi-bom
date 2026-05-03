package org.navistack.framework.expression;

import org.springframework.core.DefaultParameterNameDiscoverer;

import java.lang.reflect.Method;

public class SpringParameterNameDiscoverer implements ParameterNameDiscoverer {
    private final org.springframework.core.ParameterNameDiscoverer delegate = new DefaultParameterNameDiscoverer();

    @Override
    public String[] getParameterNames(Method method) {
        String[] names = delegate.getParameterNames(method);
        return names != null ? names : new String[0];
    }
}
