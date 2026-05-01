package org.navistack.framework.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public interface HttpServletRequestWrapperBuilder {
    HttpServletRequestWrapper build(HttpServletRequest request);
}
