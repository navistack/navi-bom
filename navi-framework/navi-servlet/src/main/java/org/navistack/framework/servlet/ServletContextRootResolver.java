package org.navistack.framework.servlet;

import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;

public interface ServletContextRootResolver {
    URI resolve(HttpServletRequest request);
}
