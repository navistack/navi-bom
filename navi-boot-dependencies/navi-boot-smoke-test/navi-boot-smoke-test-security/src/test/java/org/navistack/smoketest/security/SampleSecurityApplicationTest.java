package org.navistack.smoketest.security;

import org.junit.jupiter.api.Test;
import org.navistack.framework.core.error.UserErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SampleSecurityApplicationTest {
    private final MockMvc mockMvc;

    @Autowired
    SampleSecurityApplicationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void testRole1EchoWithRole1Token() throws Exception {
        mockMvc.perform(get("/authorized/role1/echo?content={content}", "Hello role1").header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImF1dGgiOiJST0xFX3JvbGUxIn0.ULpNYknkt9TEt24F-6ZXlfC0IKi0gftVqkJqJwbneSS4lQy1BD-Z4wEz5lttFi9Q-FpRpArH6qqxpqABoE-RUA"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.succeeded", is(true)))
                .andExpect(jsonPath("$.result.content", is("Hello role1")));
    }

    @Test
    void testRole1EchoWithRole2Token() throws Exception {
        mockMvc.perform(get("/authorized/role1/echo?content={content}", "Hello role1").header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImF1dGgiOiJST0xFX3JvbGUyIn0.rRC1XyaoaKln19xot-yErnRGxbWNc1vc78ZR2xbHMbKII4EQuL_1mkfNhTFFRWftydauzTdHAeaDpjMNfpaerA"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()))
                .andExpect(jsonPath("$.succeeded", is(false)))
                .andExpect(jsonPath("$.error", is(UserErrorCodes.AUTHORIZATION_FAILURE)));
    }

    @Test
    void testRole2EchoWithRole2Token() throws Exception {
        mockMvc.perform(get("/authorized/role2/echo?content={content}", "Hello role2").header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImF1dGgiOiJST0xFX3JvbGUyIn0.rRC1XyaoaKln19xot-yErnRGxbWNc1vc78ZR2xbHMbKII4EQuL_1mkfNhTFFRWftydauzTdHAeaDpjMNfpaerA"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.succeeded", is(true)))
                .andExpect(jsonPath("$.result.content", is("Hello role2")));
    }

    @Test
    void testRole2EchoWithRole1Token() throws Exception {
        mockMvc.perform(get("/authorized/role2/echo?content={content}", "Hello role2").header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImF1dGgiOiJST0xFX3JvbGUxIn0.ULpNYknkt9TEt24F-6ZXlfC0IKi0gftVqkJqJwbneSS4lQy1BD-Z4wEz5lttFi9Q-FpRpArH6qqxpqABoE-RUA"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()))
                .andExpect(jsonPath("$.succeeded", is(false)))
                .andExpect(jsonPath("$.error", is(UserErrorCodes.AUTHORIZATION_FAILURE)));
    }

    @Test
    void testEcho() throws Exception {
        mockMvc.perform(get("/echo?content={content}", "<a href=\"javascript:location.href='//www.example.com'\">Hello There</a>").header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMyIsImF1dGgiOiIifQ.0pIpKQj8Qn7CyFTnJvJx6NkWTSSuzIQEYYoLrGbMCOMSRbn-HuKw6sDEI6-ww56dUMr3jfBYxpjyYTAGshkQnA"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.succeeded", is(true)))
                .andExpect(jsonPath("$.result.content", is("<a href=\"javascript:location.href='//www.example.com'\">Hello There</a>")));
    }

    @Test
    void testSanitizedEcho() throws Exception {
        mockMvc.perform(get("/sanitized/echo?content={content}", "<a href=\"javascript:location.href='//www.example.com'\">Hello There</a>").header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMyIsImF1dGgiOiIifQ.0pIpKQj8Qn7CyFTnJvJx6NkWTSSuzIQEYYoLrGbMCOMSRbn-HuKw6sDEI6-ww56dUMr3jfBYxpjyYTAGshkQnA"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.succeeded", is(true)))
                .andExpect(jsonPath("$.result.content", is("Hello There")));
    }
}
