package org.navistack.framework.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class StaticServletContextRootResolverTest {
    @Test
    void testResolve() throws Exception {
        ServletContext context = Mockito.mock(ServletContext.class);
        when(context.getContextPath()).thenReturn("/example");

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getScheme()).thenReturn("http");
        when(request.getServerName()).thenReturn("example.com");
        when(request.getServerPort()).thenReturn(80);
        when(request.getServletContext()).thenReturn(context);

        StaticServletContextRootResolver resolver = new StaticServletContextRootResolver("https://www.example.com:443/");
        URI uri = resolver.resolve(request);
        assertThat(uri).isNotNull();
        assertThat(uri.getScheme()).isEqualTo("https");
        assertThat(uri.getHost()).isEqualTo("www.example.com");
        assertThat(uri.getPort()).isEqualTo(443);
        assertThat(uri.getPath()).isEqualTo("/");
        assertThat(uri.toString()).isEqualTo("https://www.example.com:443/");
    }
}
