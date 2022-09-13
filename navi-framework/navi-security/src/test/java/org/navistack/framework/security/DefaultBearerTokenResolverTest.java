package org.navistack.framework.security;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultBearerTokenResolverTest {

    @Test
    void testResolveToken() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIn0.Gfx6VO9tcxwk6xqx9yYzSfebfeakZp5JYIgP_edcw_A");
        DefaultBearerTokenResolver tokenResolver = new DefaultBearerTokenResolver();
        assertThat(tokenResolver.resolveToken(request)).isEqualTo("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIn0.Gfx6VO9tcxwk6xqx9yYzSfebfeakZp5JYIgP_edcw_A");
    }

    @Test
    void testResolveTokenWithoutTokenPresented() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        DefaultBearerTokenResolver tokenResolver = new DefaultBearerTokenResolver();
        assertThat(tokenResolver.resolveToken(request)).isNull();
    }

    @Test
    void testResolveTokenWithMultipleSpaces() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("Bearer   eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIn0.Gfx6VO9tcxwk6xqx9yYzSfebfeakZp5JYIgP_edcw_A");
        DefaultBearerTokenResolver tokenResolver = new DefaultBearerTokenResolver();
        assertThat(tokenResolver.resolveToken(request)).isNull();
    }

    @Test
    void testResolveTokenWithBearInUppercase() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("BEARER eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIn0.Gfx6VO9tcxwk6xqx9yYzSfebfeakZp5JYIgP_edcw_A");
        DefaultBearerTokenResolver tokenResolver = new DefaultBearerTokenResolver();
        assertThat(tokenResolver.resolveToken(request)).isNull();
    }

    @Test
    void testResolveTokenWithoutBearerPrefix() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIn0.Gfx6VO9tcxwk6xqx9yYzSfebfeakZp5JYIgP_edcw_A");
        DefaultBearerTokenResolver tokenResolver = new DefaultBearerTokenResolver();
        assertThat(tokenResolver.resolveToken(request)).isNull();
    }

    @Test
    void testResolveTokenWithIllegalCharacters() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9?eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIn0[Gfx6VO9tcxwk6xqx9yYzSfebfeakZp5JYIgP_edcw_A]");
        DefaultBearerTokenResolver tokenResolver = new DefaultBearerTokenResolver();
        assertThat(tokenResolver.resolveToken(request)).isNull();
    }

    @Test
    void testResolveTokenWithTokenInQuery() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("access_token")).thenReturn("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIn0.Gfx6VO9tcxwk6xqx9yYzSfebfeakZp5JYIgP_edcw_A");
        DefaultBearerTokenResolver tokenResolver = new DefaultBearerTokenResolver();
        assertThat(tokenResolver.resolveToken(request)).isEqualTo("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIn0.Gfx6VO9tcxwk6xqx9yYzSfebfeakZp5JYIgP_edcw_A");
    }

    @Test
    void testResolveTokenWithTokenPresentedInBothHeaderAndQuery() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIn0.CXHdmqgD0hdzWrbyXVFLoJ60O1jnrDb2MxNpX1TsGLtpRS5igMwZTeM1PTW3PWKKMwMoBmolbU7AG-b0exj9Uw");
        when(request.getParameter("access_token")).thenReturn("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIn0.Gfx6VO9tcxwk6xqx9yYzSfebfeakZp5JYIgP_edcw_A");
        DefaultBearerTokenResolver tokenResolver = new DefaultBearerTokenResolver();
        assertThat(tokenResolver.resolveToken(request)).isEqualTo("eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIn0.CXHdmqgD0hdzWrbyXVFLoJ60O1jnrDb2MxNpX1TsGLtpRS5igMwZTeM1PTW3PWKKMwMoBmolbU7AG-b0exj9Uw");
    }
}
