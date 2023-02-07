package org.navistack.framework.objectstorage;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.navistack.framework.servlet.DefaultServletContextRootResolver;
import org.navistack.framework.servlet.ServletContextRootResolver;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;

public class HttpServletRequestPublicRootUriSupplier implements PublicRootUriSupplier {
    @Getter
    @Setter
    @NonNull
    private ServletContextRootResolver rootResolver = new DefaultServletContextRootResolver();

    @Override
    public URI get() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes servletRequestAttributes)) {
            return null;
        }
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return rootResolver.resolve(request);
    }
}
