package org.navistack.framework.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

public class DefaultServletContextRootResolver implements ServletContextRootResolver {
    @Override
    public URI resolve(HttpServletRequest request) {
        if (request == null) {
            return null;
        }

        StringBuilder uriComponent = new StringBuilder();
        String scheme = request.getScheme();
        if (scheme != null) {
            uriComponent.append(scheme).append(":");
        }
        String serverName = request.getServerName();
        if (serverName != null) {
            uriComponent.append("//").append(serverName);

            int serverPort = request.getServerPort();
            if (serverPort >= 0) {
                uriComponent.append(":").append(serverPort);
            }
        }
        ServletContext servletContext = request.getServletContext();
        if (servletContext == null) {
            uriComponent.append("/");
        } else {
            String contextPath = servletContext.getContextPath();
            if (contextPath == null || contextPath.isEmpty()) {
                uriComponent.append("/");
            } else {
                 contextPath = contextPath.trim();
                 if (contextPath.isEmpty() || !contextPath.startsWith("/")) {
                     uriComponent.append("/");
                 } else {
                     uriComponent.append(contextPath);
                 }
            }
        }

        try {
            return new URI(uriComponent.toString());
        } catch (URISyntaxException e) {
            // do nothing and return null
            return null;
        }
    }
}
