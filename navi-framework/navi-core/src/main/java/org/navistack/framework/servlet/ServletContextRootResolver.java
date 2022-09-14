package org.navistack.framework.servlet;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

public interface ServletContextRootResolver {
    URI resolve(HttpServletRequest request);
}
