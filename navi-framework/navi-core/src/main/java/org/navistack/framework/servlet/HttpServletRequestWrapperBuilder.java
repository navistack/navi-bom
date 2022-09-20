package org.navistack.framework.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public interface HttpServletRequestWrapperBuilder<T extends HttpServletRequestWrapper> {
    T build(HttpServletRequest request);
}
