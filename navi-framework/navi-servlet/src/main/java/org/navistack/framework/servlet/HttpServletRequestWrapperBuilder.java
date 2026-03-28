package org.navistack.framework.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public interface HttpServletRequestWrapperBuilder<T extends HttpServletRequestWrapper> {
    T build(HttpServletRequest request);
}
