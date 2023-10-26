package org.navistack.framework.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.navistack.framework.servlet.HttpServletRequestWrapperBuilder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class SanitizationFilter extends GenericFilterBean {
    @Getter
    @Setter
    private HttpServletRequestWrapperBuilder httpServletRequestWrapperBuilder;

    public SanitizationFilter(HttpServletRequestWrapperBuilder httpServletRequestWrapperBuilder) {
        this.httpServletRequestWrapperBuilder = httpServletRequestWrapperBuilder;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpRequest) {
            chain.doFilter(httpServletRequestWrapperBuilder.build(httpRequest), response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
