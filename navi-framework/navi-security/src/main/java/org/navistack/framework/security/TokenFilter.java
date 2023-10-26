package org.navistack.framework.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Slf4j
public class TokenFilter extends GenericFilterBean implements InitializingBean {
    private AuthenticationManager authenticationManager;

    @Getter
    @Setter
    private BearerTokenResolver tokenResolver = new DefaultBearerTokenResolver();

    public TokenFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            String token = tokenResolver.resolveToken((HttpServletRequest) servletRequest);
            Authentication authentication = authenticationManager.authenticate(
                    TokenAuthentication.unauthenticated(token, token)
            );
            if (authentication == null) {
                return;
            }
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authentication);
            SecurityContextHolder.setContext(securityContext);
        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void afterPropertiesSet() throws ServletException {
        Assert.notNull(this.authenticationManager, "authenticationManager must be specified");
    }
}
