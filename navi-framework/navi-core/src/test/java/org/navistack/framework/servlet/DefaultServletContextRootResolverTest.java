package org.navistack.framework.servlet;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class DefaultServletContextRootResolverTest {

    @Test
    void testResolve() {
        ServletContext context = Mockito.mock(ServletContext.class);
        when(context.getContextPath()).thenReturn("/example");

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getScheme()).thenReturn("http");
        when(request.getServerName()).thenReturn("example.com");
        when(request.getServerPort()).thenReturn(80);
        when(request.getServletContext()).thenReturn(context);

        DefaultServletContextRootResolver resolver = new DefaultServletContextRootResolver();
        URI uri = resolver.resolve(request);
        assertThat(uri).isNotNull();
        assertThat(uri.getScheme()).isEqualTo("http");
        assertThat(uri.getHost()).isEqualTo("example.com");
        assertThat(uri.getPort()).isEqualTo(80);
        assertThat(uri.getPath()).isEqualTo("/example");
        assertThat(uri.toString()).isEqualTo("http://example.com:80/example");
    }

    @Test
    void testResolveWithNullRequest() {
        DefaultServletContextRootResolver resolver = new DefaultServletContextRootResolver();
        URI uri = resolver.resolve(null);
        assertThat(uri).isNull();
    }

    @Test
    void testResolveWithoutSchema() {
        ServletContext context = Mockito.mock(ServletContext.class);
        when(context.getContextPath()).thenReturn("/example");

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getServerName()).thenReturn("example.com");
        when(request.getServerPort()).thenReturn(80);
        when(request.getServletContext()).thenReturn(context);

        DefaultServletContextRootResolver resolver = new DefaultServletContextRootResolver();
        URI uri = resolver.resolve(request);
        assertThat(uri).isNotNull();
        assertThat(uri.getHost()).isEqualTo("example.com");
        assertThat(uri.getPort()).isEqualTo(80);
        assertThat(uri.getPath()).isEqualTo("/example");
        assertThat(uri.toString()).isEqualTo("//example.com:80/example");
    }

    @Test
    void testResolveWithoutHost() {
        ServletContext context = Mockito.mock(ServletContext.class);
        when(context.getContextPath()).thenReturn("/example");

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getScheme()).thenReturn("http");
        when(request.getServerPort()).thenReturn(80);
        when(request.getServletContext()).thenReturn(context);

        DefaultServletContextRootResolver resolver = new DefaultServletContextRootResolver();
        URI uri = resolver.resolve(request);
        assertThat(uri).isNotNull();
        assertThat(uri.getScheme()).isEqualTo("http");
        assertThat(uri.getPath()).isEqualTo("/example");
        assertThat(uri.toString()).isEqualTo("http:/example");
    }

    @Test
    void testResolveWithoutPort() {
        ServletContext context = Mockito.mock(ServletContext.class);
        when(context.getContextPath()).thenReturn("/example");

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getScheme()).thenReturn("http");
        when(request.getServerName()).thenReturn("example.com");
        when(request.getServletContext()).thenReturn(context);

        DefaultServletContextRootResolver resolver = new DefaultServletContextRootResolver();
        URI uri = resolver.resolve(request);
        assertThat(uri).isNotNull();
        assertThat(uri.getScheme()).isEqualTo("http");
        assertThat(uri.getHost()).isEqualTo("example.com");
        assertThat(uri.getPath()).isEqualTo("/example");
        assertThat(uri.toString()).isEqualTo("http://example.com:0/example");
    }

    @Test
    void testResolveWithoutContextPath() {
        ServletContext context = Mockito.mock(ServletContext.class);

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getScheme()).thenReturn("http");
        when(request.getServerName()).thenReturn("example.com");
        when(request.getServerPort()).thenReturn(80);
        when(request.getServletContext()).thenReturn(context);

        DefaultServletContextRootResolver resolver = new DefaultServletContextRootResolver();
        URI uri = resolver.resolve(request);
        assertThat(uri).isNotNull();
        assertThat(uri.getScheme()).isEqualTo("http");
        assertThat(uri.getHost()).isEqualTo("example.com");
        assertThat(uri.getPort()).isEqualTo(80);
        assertThat(uri.toString()).isEqualTo("http://example.com:80/");
    }

    @Test
    void testResolveWithoutContext() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getScheme()).thenReturn("http");
        when(request.getServerName()).thenReturn("example.com");
        when(request.getServerPort()).thenReturn(80);

        DefaultServletContextRootResolver resolver = new DefaultServletContextRootResolver();
        URI uri = resolver.resolve(request);
        assertThat(uri).isNotNull();
        assertThat(uri.getScheme()).isEqualTo("http");
        assertThat(uri.getHost()).isEqualTo("example.com");
        assertThat(uri.getPort()).isEqualTo(80);
        assertThat(uri.toString()).isEqualTo("http://example.com:80/");
    }
}
