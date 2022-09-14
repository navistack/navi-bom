package org.navistack.framework.servlet;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

public class StaticServletContextRootResolver implements ServletContextRootResolver {
    private final URI uriComponent;

    public StaticServletContextRootResolver(URI uriComponent) {
        if (uriComponent == null) {
            throw new NullPointerException("uriComponent must not be null");
        }
        this.uriComponent = uriComponent;
    }

    public StaticServletContextRootResolver(String uriComponent) throws URISyntaxException {
        if (uriComponent == null) {
            throw new NullPointerException("uriComponent must not be null");
        }
        this.uriComponent = new URI(uriComponent);
    }

    @Override
    public URI resolve(HttpServletRequest request) {
        return uriComponent;
    }
}
