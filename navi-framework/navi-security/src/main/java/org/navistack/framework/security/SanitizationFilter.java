package org.navistack.framework.security;

import lombok.Getter;
import lombok.Setter;
import org.navistack.framework.servlet.HttpServletRequestWrapperBuilder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SanitizationFilter extends GenericFilterBean {
    @Getter
    @Setter
    private HttpServletRequestWrapperBuilder httpServletRequestWrapperBuilder;

    public SanitizationFilter(HttpServletRequestWrapperBuilder httpServletRequestWrapperBuilder) {
        this.httpServletRequestWrapperBuilder = httpServletRequestWrapperBuilder;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            chain.doFilter(httpServletRequestWrapperBuilder.build((HttpServletRequest) request), response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
