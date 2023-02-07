package org.navistack.smoketest.ratelimit;

import org.junit.jupiter.api.Test;
import org.navistack.boot.testsupport.testcontainers.RedisContainer;
import org.navistack.framework.core.error.UserErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers(disabledWithoutDocker = true)
class SampleRateLimitApplicationTest {
    @Container
    static RedisContainer redis = new RedisContainer();

    private final MockMvc mockMvc;

    @DynamicPropertySource
    static void applicationProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", redis::getHost);
        registry.add("spring.data.redis.port", redis::getFirstMappedPort);
    }

    @Autowired
    SampleRateLimitApplicationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void testSlidingWindowRateLimitedPing() throws Exception {
        mockMvc.perform(get("/rate-limited/sliding-window/ping"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.succeeded", is(true)));
        mockMvc.perform(get("/rate-limited/sliding-window/ping"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.TOO_MANY_REQUESTS.value()))
                .andExpect(jsonPath("$.succeeded", is(false)))
                .andExpect(jsonPath("$.error", is(UserErrorCodes.FREQUENT_REQUEST)));
        Thread.sleep(2000);
        mockMvc.perform(get("/rate-limited/sliding-window/ping"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.succeeded", is(true)));
    }

    @Test
    void testFixedWindowRateLimitedPing() throws Exception {
        mockMvc.perform(get("/rate-limited/fixed-window/ping"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.succeeded", is(true)));
        mockMvc.perform(get("/rate-limited/fixed-window/ping"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.TOO_MANY_REQUESTS.value()))
                .andExpect(jsonPath("$.succeeded", is(false)))
                .andExpect(jsonPath("$.error", is(UserErrorCodes.FREQUENT_REQUEST)));
        Thread.sleep(1000);
        mockMvc.perform(get("/rate-limited/fixed-window/ping"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.succeeded", is(true)));
    }
}
